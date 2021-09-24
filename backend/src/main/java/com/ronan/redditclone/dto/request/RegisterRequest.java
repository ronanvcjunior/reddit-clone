package com.ronan.redditclone.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String username;
    private String password;
}