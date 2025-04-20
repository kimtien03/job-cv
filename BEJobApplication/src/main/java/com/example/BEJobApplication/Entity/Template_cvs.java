package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "template_cvs")
public class Template_cvs {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "position_id", nullable = false)
    private Integer positionId;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer styleId;

    @NotNull
    @Column(name = "image", nullable = false)
    private String image;

    // Constructors
    public Template_cvs() {}

    public Template_cvs(Integer id, Integer positionId, Integer styleId, String image) {
        this.id = id;
        this.positionId = positionId;
        this.styleId = styleId;
        this.image = image;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // toString
    @Override
    public String toString() {
        return "Template_cvs{" +
                "id=" + id +
                ", positionId=" + positionId +
                ", styleId=" + styleId +
                ", image=" + image +
                '}';
    }


}
