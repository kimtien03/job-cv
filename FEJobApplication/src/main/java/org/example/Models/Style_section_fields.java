package org.example.Models;

public class Style_section_fields {

    private Integer id;

    private Integer section_id;

    private Integer style_id;

    private Integer field_id;

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

}
