package com.ronan.redditclone.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    private String text;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public Comment() {
    }

    public Comment(Long id, String text, Post post, Instant createdDate, User user) {
        this.id = id;
        this.text = text;
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment id(Long id) {
        setId(id);
        return this;
    }

    public Comment text(String text) {
        setText(text);
        return this;
    }

    public Comment post(Post post) {
        setPost(post);
        return this;
    }

    public Comment createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public Comment user(User user) {
        setUser(user);
        return this;
    }
}