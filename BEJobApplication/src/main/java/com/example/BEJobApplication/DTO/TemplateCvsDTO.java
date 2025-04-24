package com.example.BEJobApplication.DTO;

import jakarta.validation.constraints.NotNull;

public class TemplateCvsDTO {

    private Integer id;

    @NotNull
    private Integer positionId;

    @NotNull
    private Integer styleId;

    @NotNull
    private String image;
    // Constructors
    public TemplateCvsDTO() {
    }

    public TemplateCvsDTO(Integer id, Integer positionId, Integer styleId, String image) {
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

    @Override
    public String toString() {
        return "TemplateCvsDTO{" +
                "id=" + id +
                ", positionId=" + positionId +
                ", styleId=" + styleId +
                ", image=" + image +
                '}';
    }
}
