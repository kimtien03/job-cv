package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "template_cvs")
public class Template_cvs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Positions positions;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Styles style;

    @Column(name = "image", length = 255)
    private String image;

    // Constructors
    public Template_cvs() {
    }

    public Template_cvs(Integer id, Positions positions, Styles style,String image) {
        this.id = id;
        this.positions = positions;
        this.style = style;
        this.image = image;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
