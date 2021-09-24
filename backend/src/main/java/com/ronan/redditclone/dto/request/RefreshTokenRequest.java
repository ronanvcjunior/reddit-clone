package com.ronan.redditclone.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String refreshToken;

    private String username;
}