package com.ronan.redditclone.controller;

import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.request.LoginRequest;
import com.ronan.redditclone.dto.request.RegisterRequest;
import com.ronan.redditclone.dto.response.AuthenticationResponse;
import com.ronan.redditclone.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;
    
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest) {
        User user = service.signup(registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<User> verifyAccount(@PathVariable String token) {
        User user = service.verifyAccount(token);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = service.login(loginRequest);

        return ResponseEntity.ok().body(authenticationResponse);
    }
}
