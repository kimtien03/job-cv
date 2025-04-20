package org.example.Models;


public class Sections {
    private Integer id;

    private String sections_key;

    private String section_name;

    // Constructors
    public Sections() {
    }

    public Sections(Integer id, String sections_key, String section_name) {
        this.id = id;
        this.sections_key = sections_key;
        this.section_name = section_name;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSections_key() {
        return sections_key;
    }

    public void setSections_key(String sections_key) {
        this.sections_key = sections_key;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
}
