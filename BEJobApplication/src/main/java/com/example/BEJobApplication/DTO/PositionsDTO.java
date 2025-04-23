package com.example.BEJobApplication.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PositionsDTO {

    private Integer id;

    @Size(max = 255)
    @NotNull
    private String name;

    @NotNull
    private Integer industryId;  // Chứa ID của ngành (Industry)

    // Constructors
    public PositionsDTO() {}

    public PositionsDTO(Integer id, String name, Integer industryId) {
        this.id = id;
        this.name = name;
        this.industryId = industryId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    @Override
    public String toString() {
        return "PositionsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", industryId=" + industryId +
                '}';
    }
}
