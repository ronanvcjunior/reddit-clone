package com.ronan.redditclone.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    
    @NotBlank
    private String refreshToken;

    private String username;
}