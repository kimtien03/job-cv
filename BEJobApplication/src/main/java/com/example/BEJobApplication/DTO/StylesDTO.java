package com.example.BEJobApplication.DTO;

public class StylesDTO {
    private Integer id;
    private String styleName;
    private String fileName;
    private String cssName;

    public StylesDTO() {
    }

    public StylesDTO(Integer id, String styleName, String fileName, String cssName) {
        this.id = id;
        this.styleName = styleName;
        this.fileName = fileName;
        this.cssName = cssName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCssName() {
        return cssName;
    }

    public void setCssName(String cssName) {
        this.cssName = cssName;
    }
}
