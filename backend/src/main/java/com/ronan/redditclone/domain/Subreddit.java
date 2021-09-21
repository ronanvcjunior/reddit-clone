package com.ronan.redditclone.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Subreddit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Community name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = LAZY)
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    private User user;

    public Subreddit() {
    }

    public Subreddit(Long id, String name, String description, List<Post> posts, Instant createdDate, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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

    public Subreddit id(Long id) {
        setId(id);
        return this;
    }

    public Subreddit name(String name) {
        setName(name);
        return this;
    }

    public Subreddit description(String description) {
        setDescription(description);
        return this;
    }

    public Subreddit posts(List<Post> posts) {
        setPosts(posts);
        return this;
    }

    public Subreddit createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public Subreddit user(User user) {
        setUser(user);
        return this;
    }
}