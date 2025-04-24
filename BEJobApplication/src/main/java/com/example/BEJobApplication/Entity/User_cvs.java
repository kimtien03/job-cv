package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_cvs")
public class User_cvs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "templates_id", nullable = false)
    private Template_cvs templates;

    @NotNull
    @Column(name = "cv_name", nullable = false)
    private String cv_name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Styles style;

    @CreationTimestamp
    @Column(name = "createAt", updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updateAt")
    private LocalDateTime updateAt;

    // Constructors
    public User_cvs() {
    }

    // Getters and Setters (nếu dùng Lombok thì có thể thay bằng @Getter @Setter)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Template_cvs getTemplates() {
        return templates;
    }

    public void setTemplates(Template_cvs templates) {
        this.templates = templates;
    }

    public String getCv_name() {
        return cv_name;
    }

    public void setCv_name(String cv_name) {
        this.cv_name = cv_name;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
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