package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "style_section_fields")
public class Style_section_fields {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "section_id", nullable = false)
    private Integer section_id;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer style_id;

    @NotNull
    @Column(name = "field_id", nullable = false)
    private Integer field_id;

    @NotNull
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    // Constructors
    public Style_section_fields() {}

    public Style_section_fields(Integer id, Integer section_id, Integer style_id, Integer field_id, Boolean visible) {
        this.id = id;
        this.section_id = section_id;
        this.style_id = style_id;
        this.field_id = field_id;
        this.visible = visible;
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

    public Integer getField_id() {
        return field_id;
    }

    public void setField_id(Integer field_id) {
        this.field_id = field_id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    // toString
    @Override
    public String toString() {
        return "Style_section_fields{" +
                "id=" + id +
                ", section_id=" + section_id +
                ", style_id=" + style_id +
                ", field_id=" + field_id +
                ", visible=" + visible +
                '}';
    }
}
