package com.ronan.redditclone.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.ronan.redditclone.domain.NotificationEmail;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.domain.VerificationToken;
import com.ronan.redditclone.dto.request.LoginRequest;
import com.ronan.redditclone.dto.request.RegisterRequest;
import com.ronan.redditclone.dto.response.AuthenticationResponse;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.repository.UserRepository;
import com.ronan.redditclone.repository.VerificationTokenRepository;
import com.ronan.redditclone.security.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;
    
    public User signup(RegisterRequest registerRequest) {
        User user = signupDate(registerRequest);

        User userS = repository.save(user);

        // generateVerificationMail(userS);

        return userS;
    }
 
    private User signupDate(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        // user.setEnabled(false);
        user.setEnabled(true);
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

    public User verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        return fetchUserAndEnable(verificationToken.get());
    }

    private User fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = repository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        user.setEnabled(true);
        return repository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

}
