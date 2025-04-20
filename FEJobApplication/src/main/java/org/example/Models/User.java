package org.example.Models;

import java.time.LocalDate;

public class User {

    private Integer id;

    private String username;

    private String email;

    private String password;

    private LocalDate birth_day;

    private String gender;

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

    public LocalDate getBirth_day() { return birth_day; }
    public void setBirth_day(LocalDate birth_day) { this.birth_day = birth_day; }


    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

}
