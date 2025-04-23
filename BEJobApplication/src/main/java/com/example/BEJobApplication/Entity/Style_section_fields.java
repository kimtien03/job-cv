package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "style_section_fields")
public class Style_section_fields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Sections section;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Styles style;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Fields field;

    @NotNull
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    // Constructors
    public Style_section_fields() {}

    public Style_section_fields(Integer id, Sections section, Styles style, Fields field, Boolean visible) {
        this.id = id;
        this.section = section;
        this.style = style;
        this.field = field;
        this.visible = visible;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sections getSection() {
        return section;
    }

    public void setSection(Sections section) {
        this.section = section;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }

    public Fields getField() {
        return field;
    }

    public void setField(Fields field) {
        this.field = field;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Style_section_fields{" +
                "id=" + id +
                ", section=" + (section != null ? section.getId() : null) +
                ", style=" + (style != null ? style.getId() : null) +
                ", field=" + (field != null ? field.getId() : null) +
                ", visible=" + visible +
                '}';
    }
}
