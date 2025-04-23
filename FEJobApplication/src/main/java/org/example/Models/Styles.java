package org.example.Models;

public class Styles {

    private Integer id;

    private String styleName;

    private String fileName;

    private String cssName;

    // Constructors
    public Styles() {}

    public Styles(Integer id, String styleName, String fileName, String cssName) {
        this.id = id;
        this.styleName = styleName;
        this.fileName = fileName;
        this.cssName = cssName;
    }

    // Getters and Setters
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
