package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_cvs")
public class User_cvs {

    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @NotNull
    @Column(name = "templates_id", nullable = false)
    private Integer templates_id;

    @NotNull
    @Column(name = "cv_name", nullable = false)
    private String cv_name;

    @NotNull
    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer style_id;

    @NotNull
    @Column(name = "updateDate", nullable = false)
    private LocalDateTime  updateDate;

    // Constructor mặc định
    public User_cvs() {}

    // Constructor đầy đủ
    public User_cvs(Integer id, Integer user_id, Integer templates_id, String cv_name, LocalDateTime  createDate, Integer style_id, LocalDateTime  updateDate) {
        this.id = id;
        this.user_id = user_id;
        this.templates_id = templates_id;
        this.cv_name = cv_name;
        this.createDate = createDate;
        this.style_id = style_id;
        this.updateDate = updateDate;
    }

    // Getter và Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTemplates_id() {
        return templates_id;
    }

    public void setTemplates_id(Integer templates_id) {
        this.templates_id = templates_id;
    }

    public String getCv_name() {
        return cv_name;
    }

    public void setCv_name(String cv_name) {
        this.cv_name = cv_name;
    }

    public LocalDateTime  getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime  createDate) {
        this.createDate = createDate;
    }

    public Integer getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

    public LocalDateTime  getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime  updateDate) {
        this.updateDate = updateDate;
    }

    // toString()
    @Override
    public String toString() {
        return "User_cvs{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", templates_id=" + templates_id +
                ", cv_name='" + cv_name + '\'' +
                ", createDate=" + createDate +
                ", style_id=" + style_id +
                ", updateDate=" + updateDate +
                '}';
    }
}
