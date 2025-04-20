package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "styles")
public class Styles {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "style_name", nullable = false)
    private String style_name;

    @NotNull
    @Column(name = "file_name", nullable = false)
    private String file_name;

    @NotNull
    @Column(name = "css_name", nullable = false)
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

    // toString
    @Override
    public String toString() {
        return "Styles{" +
                "id=" + id +
                ", style_name='" + style_name + '\'' +
                ", file_name='" + file_name + '\'' +
                ", css_name='" + css_name + '\'' +
                '}';
    }
}
