package com.ronan.redditclone.dto;

import javax.validation.constraints.NotBlank;

public class LogoutRequest {
    @NotBlank
    private String refreshToken;

    public LogoutRequest() {
    }

    public LogoutRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LogoutRequest refreshToken(String refreshToken) {
        setRefreshToken(refreshToken);
        return this;
    }
}