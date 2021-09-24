package com.ronan.redditclone.dto.response;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;

    public AuthenticationResponse(String authenticationToken, String username) {
        this.authenticationToken = authenticationToken;
        this.username = username;
    }
}