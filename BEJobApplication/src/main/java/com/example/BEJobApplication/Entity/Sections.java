package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sections")
public class Sections {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "section_key", nullable = false)
    private String sections_key;

    @Size(max = 255)
    @NotNull
    @Column(name = "section_name", nullable = false)
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

    // toString
    @Override
    public String toString() {
        return "Sections{" +
                "id=" + id +
                ", sections_key='" + sections_key + '\'' +
                ", section_name='" + section_name + '\'' +
                '}';
    }
}
