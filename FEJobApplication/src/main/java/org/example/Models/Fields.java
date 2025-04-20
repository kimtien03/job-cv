package org.example.Models;

public class Fields {

    private Integer id;

    private Integer section_id;

    private String field_name;

    private String field_type;

    // Constructors
    public Fields() {}

    public Fields(Integer id, Integer section_id, String field_name, String field_type) {
        this.id = id;
        this.section_id = section_id;
        this.field_name = field_name;
        this.field_type = field_type;
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

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

}
