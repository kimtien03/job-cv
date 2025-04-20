package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "template_data")
public class Template_data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "template_cv_id", nullable = false)
    private Template_cvs templateCvs;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Sections section;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Fields field;

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    @NotNull
    @Column(name = "group_index", nullable = false)
    private Integer groupIndex;

    public Template_data() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Template_cvs getTemplateCvs() {
        return templateCvs;
    }

    public void setTemplateCvs(Template_cvs templateCvs) {
        this.templateCvs = templateCvs;
    }

    public Sections getSection() {
        return section;
    }

    public void setSection(Sections section) {
        this.section = section;
    }

    public Fields getField() {
        return field;
    }

    public void setField(Fields field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }
}
