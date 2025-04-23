package com.example.BEJobApplication.DTO;

public class StyleSectionFieldsDTO {
    private Integer id;
    private Integer sectionId;
    private Integer styleId;
    private Integer fieldId;
    private Boolean visible;

    // Constructors
    public StyleSectionFieldsDTO() {}

    public StyleSectionFieldsDTO(Integer id, Integer sectionId, Integer styleId, Integer fieldId, Boolean visible) {
        this.id = id;
        this.sectionId = sectionId;
        this.styleId = styleId;
        this.fieldId = fieldId;
        this.visible = visible;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "StyleSectionFieldsDTO{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", styleId=" + styleId +
                ", fieldId=" + fieldId +
                ", visible=" + visible +
                '}';
    }
}
