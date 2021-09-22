package com.ronan.redditclone.service;

import java.time.Instant;

import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.request.RegisterRequest;
import com.ronan.redditclone.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    
    public User signup(RegisterRequest registerRequest) {
        User user = signupDate(registerRequest);
        return repository.save(user);
    }

    private User signupDate(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreated(Instant.now());
        user.setEnabled(false);
        return user;
    }


}
