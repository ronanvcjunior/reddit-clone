package com.ronan.redditclone.controller;

import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.request.RegisterRequest;
import com.ronan.redditclone.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;
    
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest) {
        User user = service.signup(registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
