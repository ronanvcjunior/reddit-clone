package com.ronan.redditclone.domain;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private User user;
    
    private String token;
    private Instant expiryDate;

    public VerificationToken() {
    }

    public VerificationToken(Long id, User user, String token, Instant expiryDate) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public VerificationToken id(Long id) {
        setId(id);
        return this;
    }

    public VerificationToken user(User user) {
        setUser(user);
        return this;
    }

    public VerificationToken token(String token) {
        setToken(token);
        return this;
    }

    public VerificationToken expiryDate(Instant expiryDate) {
        setExpiryDate(expiryDate);
        return this;
    }
}