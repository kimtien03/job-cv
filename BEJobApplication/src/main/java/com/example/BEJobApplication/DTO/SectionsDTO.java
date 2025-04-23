package com.example.BEJobApplication.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SectionsDTO {

    private Integer id;

    @Size(max = 255)
    @NotNull
    private String sectionsKey;

    @Size(max = 255)
    @NotNull
    private String sectionName;

    // Constructors
    public SectionsDTO() {
    }

    public SectionsDTO(Integer id, String sectionsKey, String sectionName) {
        this.id = id;
        this.sectionsKey = sectionsKey;
        this.sectionName = sectionName;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionsKey() {
        return sectionsKey;
    }

    public void setSectionsKey(String sectionsKey) {
        this.sectionsKey = sectionsKey;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    // toString
    @Override
    public String toString() {
        return "SectionsDTO{" +
                "id=" + id +
                ", sectionsKey='" + sectionsKey + '\'' +
                ", sectionName='" + sectionName + '\'' +
                '}';
    }
}
