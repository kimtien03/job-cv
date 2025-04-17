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
    private Integer section_id;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer style_id;

    // Constructors
    public Template_cvs() {}

    public Template_cvs(Integer id, Integer section_id, Integer style_id) {
        this.id = id;
        this.section_id = section_id;
        this.style_id = style_id;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public Integer getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

    // toString
    @Override
    public String toString() {
        return "Template_cvs{" +
                "id=" + id +
                ", section_id=" + section_id +
                ", style_id=" + style_id +
                '}';
    }
}
