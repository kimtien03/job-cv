package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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
    @Column(name = "createAt", nullable = false)
    private LocalDateTime createAt;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer style_id;

    @NotNull
    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getTemplates_id() {
        return templates_id;
    }

    public String getCv_name() {
        return cv_name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Integer getStyle_id() {
        return style_id;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setTemplates_id(Integer templates_id) {
        this.templates_id = templates_id;
    }

    public void setCv_name(String cv_name) {
        this.cv_name = cv_name;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    // toString()
    @Override
    public String toString() {
        return "User_cvs{"
                + "id=" + id
                + ", user_id=" + user_id
                + ", templates_id=" + templates_id
                + ", cv_name='" + cv_name + '\''
                + ", createDate=" + createAt
                + ", style_id=" + style_id
                + ", updateDate=" + updateAt
                + '}';
        
    }
}
