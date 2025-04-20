package org.example.Models;

public class Styles {

    private Integer id;

    private String style_name;

    private String file_name;

    private String css_name;

    // Constructors
    public Styles() {}

    public Styles(Integer id, String style_name, String file_name, String css_name) {
        this.id = id;
        this.style_name = style_name;
        this.file_name = file_name;
        this.css_name = css_name;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getCss_name() {
        return css_name;
    }

    public void setCss_name(String css_name) {
        this.css_name = css_name;
    }

}
