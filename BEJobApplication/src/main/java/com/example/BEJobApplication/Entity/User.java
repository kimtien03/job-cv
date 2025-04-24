package com.example.BEJobApplication.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "username")
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "email")
    private String email;

//    @JsonIgnore
    @Column(name = "password")
    private String password;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_day")
    private LocalDate birth_day;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(LocalDate birth_day) {
        this.birth_day = birth_day;
    }

    public LocalDate getBirth_date() {
        return birth_day;
    }

    public void setBirth_date(LocalDate Birth_day) {
        this.birth_day = Birth_day;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", birth_date=" + birth_day
                + ", gender=" + gender
                + ", password=" + password
                + '}';
    }
}
