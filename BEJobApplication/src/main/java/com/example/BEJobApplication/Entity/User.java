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
    @Column(name = "birth_date")
    private LocalDate birth_date;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "role")
    private String role;

    public User() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getBirth_date() { return birth_date; }
    public void setBirth_date(LocalDate birth_date) { this.birth_date = birth_date; }

    public Boolean getGender() { return gender; }
    public void setGender(Boolean gender) { this.gender = gender; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birth_date=" + birth_date +
                ", gender=" + gender +
                ", password=" + password +
                '}';
    }
}
