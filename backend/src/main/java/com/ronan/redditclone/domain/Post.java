package com.ronan.redditclone.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;

    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;
    
    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    private Integer voteCount = 0;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;

    public Post() {
    }

    public Post(Long postId, String postName, String url, String description, Integer voteCount, User user, Instant createdDate, Subreddit subreddit) {
        this.postId = postId;
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.voteCount = voteCount;
        this.user = user;
        this.createdDate = createdDate;
        this.subreddit = subreddit;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return this.postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Subreddit getSubreddit() {
        return this.subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }

    public Post postId(Long postId) {
        setPostId(postId);
        return this;
    }

    public Post postName(String postName) {
        setPostName(postName);
        return this;
    }

    public Post url(String url) {
        setUrl(url);
        return this;
    }

    public Post description(String description) {
        setDescription(description);
        return this;
    }

    public Post voteCount(Integer voteCount) {
        setVoteCount(voteCount);
        return this;
    }

    public Post user(User user) {
        setUser(user);
        return this;
    }

    public Post createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public Post subreddit(Subreddit subreddit) {
        setSubreddit(subreddit);
        return this;
    }
}