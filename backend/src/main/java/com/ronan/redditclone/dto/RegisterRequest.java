package com.ronan.redditclone.dto;

public class RegisterRequest {
    private String email;
    private String username;
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterRequest email(String email) {
        setEmail(email);
        return this;
    }

    public RegisterRequest username(String username) {
        setUsername(username);
        return this;
    }

    public RegisterRequest password(String password) {
        setPassword(password);
        return this;
    }
}