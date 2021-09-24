package com.ronan.redditclone.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank
    private String refreshToken;
}