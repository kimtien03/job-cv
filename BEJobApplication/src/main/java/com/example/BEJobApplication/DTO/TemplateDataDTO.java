package com.example.BEJobApplication.DTO;

public class TemplateDataDTO {
    private Integer id;
    private Integer templateCvId;
    private Integer sectionId;
    private Integer fieldId;
    private String value;
    private Integer groupIndex;

    public TemplateDataDTO() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateCvId() {
        return templateCvId;
    }

    public void setTemplateCvId(Integer templateCvId) {
        this.templateCvId = templateCvId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }
}
