package com.example.BEJobApplication.DTO;

public class FieldsDTO {
    private Integer id;
    private Integer sectionId;  // Chỉ lấy ID của Section
    private String fieldName;
    private String fieldType;

    // Constructors
    public FieldsDTO() {
    }

    public FieldsDTO(Integer id, Integer sectionId, String fieldName, String fieldType) {
        this.id = id;
        this.sectionId = sectionId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    // toString
    @Override
    public String toString() {
        return "FieldsDTO{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                '}';
    }
}
