package com.ronan.redditclone.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class RefreshToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private Instant createdDate;

    public RefreshToken() {
    }

    public RefreshToken(Long id, String token, Instant createdDate) {
        this.id = id;
        this.token = token;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public RefreshToken id(Long id) {
        setId(id);
        return this;
    }

    public RefreshToken token(String token) {
        setToken(token);
        return this;
    }

    public RefreshToken createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }
}