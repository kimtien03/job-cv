package org.example.Models;

public class Template_data {

    private Integer id;

    private Integer template_cv_id;

    private Integer section_id;

    private Integer field_id;

    private String value;

    private Integer group_index;

    // Default constructor
    public Template_data() {}

    // Parameterized constructor
    public Template_data(Integer id, Integer template_cv_id, Integer section_id, Integer field_id, String value, Integer group_index) {
        this.id = id;
        this.template_cv_id = template_cv_id;
        this.section_id = section_id;
        this.field_id = field_id;
        this.value = value;
        this.group_index = group_index;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplate_cv_id() {
        return template_cv_id;
    }

    public void setTemplate_cv_id(Integer template_cv_id) {
        this.template_cv_id = template_cv_id;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public Integer getField_id() {
        return field_id;
    }

    public void setField_id(Integer field_id) {
        this.field_id = field_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getGroup_index() {
        return group_index;
    }

    public void setGroup_index(Integer group_index) {
        this.group_index = group_index;
    }
}
