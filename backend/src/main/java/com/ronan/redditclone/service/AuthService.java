package com.ronan.redditclone.service;

import java.time.Instant;
import java.util.UUID;

import com.ronan.redditclone.domain.NotificationEmail;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.domain.VerificationToken;
import com.ronan.redditclone.dto.request.RegisterRequest;
import com.ronan.redditclone.repository.UserRepository;
import com.ronan.redditclone.repository.VerificationTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private MailService mailService;
    
    public User signup(RegisterRequest registerRequest) {
        User user = signupDate(registerRequest);

        User userS = repository.save(user);

        generateVerificationMail(userS);

        return userS;
    }
 
    private User signupDate(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        return user;
    }
    
    private void generateVerificationMail(User user) {
        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Please Activate you  Account", user.getEmail(), 
                "Thank you for signing up to Spring Reddit, "
                + "please click on the below url to activate your account : "
                + "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        return token;
    }

}
