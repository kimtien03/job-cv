package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "template_data")
public class Template_data {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "template_cv_id", nullable = false)
    private Integer template_cv_id;

    @NotNull
    @Column(name = "section_id", nullable = false)
    private Integer section_id;

    @NotNull
    @Column(name = "field_id", nullable = false)
    private Integer field_id;

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    @NotNull
    @Column(name = "group_index", nullable = false)
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
