package org.example.Models;


public class Template_cvs {

    private Integer id;

    private Integer positionId;

    private Integer styleId;
    private String Image;

    // Constructors
    public Template_cvs() {}

    public Template_cvs(Integer id, Integer positionId, Integer styleId, String Image) {
        this.id = id;
        this.positionId = positionId;
        this.styleId = styleId;
        this.Image = Image;
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

    public void setPositionId(Integer section_id) {
        this.positionId = section_id;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer style_id) {
        this.styleId = style_id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
