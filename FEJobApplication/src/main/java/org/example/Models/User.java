package org.example.Models;


import java.time.LocalDate;

public class User {

    private Integer id;

    private String username;

    private String email;

    private String password;

    private LocalDate birth_date;

    private Boolean gender;

    public User() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getBirth_date() { return birth_date; }
   public void setBirth_date(LocalDate Birth_date) { this.birth_date = Birth_date; }

    public Boolean getGender() { return gender; }
    public void setGender(Boolean gender) { this.gender = gender; }
}
