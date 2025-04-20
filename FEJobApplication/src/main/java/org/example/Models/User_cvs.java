package org.example.Models;

import java.time.LocalDateTime;

public class User_cvs {

    private Integer id;

    private Integer user_id;

    private Integer templates_id;

    private String cv_name;

    private LocalDateTime createAt;

    private Integer style_id;

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

}
