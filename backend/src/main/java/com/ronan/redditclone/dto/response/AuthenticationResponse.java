package com.ronan.redditclone.dto.response;

import java.time.Instant;

public class AuthenticationResponse {
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String authenticationToken, String username) {
        this.authenticationToken = authenticationToken;
        this.username = username;
    }

    public AuthenticationResponse(String authenticationToken, String refreshToken, Instant expiresAt, String username) {
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.username = username;
    }

    public String getAuthenticationToken() {
        return this.authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthenticationResponse authenticationToken(String authenticationToken) {
        setAuthenticationToken(authenticationToken);
        return this;
    }

    public AuthenticationResponse refreshToken(String refreshToken) {
        setRefreshToken(refreshToken);
        return this;
    }

    public AuthenticationResponse expiresAt(Instant expiresAt) {
        setExpiresAt(expiresAt);
        return this;
    }

    public AuthenticationResponse username(String username) {
        setUsername(username);
        return this;
    }
}