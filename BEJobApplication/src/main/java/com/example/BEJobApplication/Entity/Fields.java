package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "fields")
public class Fields {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Sections section;

    @NotNull
    @Column(name = "field_name", nullable = false)
    private String field_name;

    @NotNull
    @Column(name = "field_type", nullable = false)
    private String field_type;

    // Constructors
    public Fields() {
    }

    public Fields(Integer id, Sections section, String field_name, String field_type) {
        this.id = id;
        this.section = section;
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

    public Sections getSection() {
        return section;
    }

    public void setSection(Sections section) {
        this.section = section;
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

    // toString

    @Override
    public String toString() {
        return "Fields{" +
                "id=" + id +
                ", section=" + (section != null ? section.getId() : null) +
                ", field_name='" + field_name + '\'' +
                ", field_type='" + field_type + '\'' +
                '}';
    }
}
