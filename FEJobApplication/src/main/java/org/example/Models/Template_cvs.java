package org.example.Models;


public class Template_cvs {

    private Integer id;

    private Integer positionId;

    private Integer styleId;
    private String image;

    // Constructors
    public Template_cvs() {}

    public Template_cvs(Integer id, Integer positionId, Integer styleId, String image) {
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
}
