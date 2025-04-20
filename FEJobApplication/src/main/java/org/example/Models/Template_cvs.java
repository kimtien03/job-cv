package org.example.Models;


public class Template_cvs {

    private Integer id;

    private Integer section_id;

    private Integer style_id;

    // Constructors
    public Template_cvs() {}

    public Template_cvs(Integer id, Integer section_id, Integer style_id) {
        this.id = id;
        this.section_id = section_id;
        this.style_id = style_id;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public Integer getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

}
