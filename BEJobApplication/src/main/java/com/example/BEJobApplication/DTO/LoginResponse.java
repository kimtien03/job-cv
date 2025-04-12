package com.example.BEJobApplication.DTO;

import com.example.BEJobApplication.Entity.User;

// LoginResponse.java
public class LoginResponse {
    private String message;
    private String token; // nếu dùng JWT, có thể để null nếu không dùng
    private User user;

    public LoginResponse(String message, String token,User user) {
        this.message = message;
        this.token = token;
        this.user=user;
    }


    // Getters và setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
