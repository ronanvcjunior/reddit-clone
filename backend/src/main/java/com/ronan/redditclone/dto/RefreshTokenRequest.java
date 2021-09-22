package com.ronan.redditclone.dto;

import javax.validation.constraints.NotBlank;

public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;

    private String username;

    public RefreshTokenRequest() {
    }

    public RefreshTokenRequest(String refreshToken, String username) {
        this.refreshToken = refreshToken;
        this.username = username;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RefreshTokenRequest refreshToken(String refreshToken) {
        setRefreshToken(refreshToken);
        return this;
    }

    public RefreshTokenRequest username(String username) {
        setUsername(username);
        return this;
    }
}