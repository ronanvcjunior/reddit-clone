package com.ronan.redditclone.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;

    public AuthenticationResponse(String authenticationToken, String username) {
        this.authenticationToken = authenticationToken;
        this.username = username;
    }
}