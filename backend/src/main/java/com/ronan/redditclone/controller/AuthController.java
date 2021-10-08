package com.ronan.redditclone.controller;

import java.util.List;

import javax.validation.Valid;

import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.UserDto;
import com.ronan.redditclone.dto.request.LoginRequest;
import com.ronan.redditclone.dto.request.RefreshTokenRequest;
import com.ronan.redditclone.dto.request.RegisterRequest;
import com.ronan.redditclone.dto.response.AuthenticationResponse;
import com.ronan.redditclone.service.AuthService;
import com.ronan.redditclone.service.RefreshTokenService;

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

    private final RefreshTokenService refreshTokenService;
    
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

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return service.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok().body("Refresh Token Deleted Successfully!!");
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser() {
        User user = service.getCurrentUser();
        return ResponseEntity.ok().body(user);
    }
    
    @GetMapping("/user/{username}")
    public ResponseEntity<Boolean> checkForUsername (@PathVariable String username) {
        Boolean userConflict = service.checkForUsername(username);
        return ResponseEntity.ok().body(userConflict);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> users = service.findAllUsers();
        return ResponseEntity.ok().body(users);
    }
}
