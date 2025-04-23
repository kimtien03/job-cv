package com.example.BEJobApplication.DTO;

import java.time.LocalDateTime;

public class UserCvsDTO {

    private Integer id;
    private Integer userId;
    private Integer templateId;
    private String cvName;
    private Integer styleId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // Constructors
    public UserCvsDTO() {}

    public UserCvsDTO(Integer id, Integer userId, Integer templateId, String cvName, Integer styleId, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userId = userId;
        this.templateId = templateId;
        this.cvName = cvName;
        this.styleId = styleId;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
