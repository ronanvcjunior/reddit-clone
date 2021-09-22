package com.ronan.redditclone.dto;

import java.time.Instant;

public class CommentsDto {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;

    public CommentsDto() {
    }

    public CommentsDto(Long id, Long postId, Instant createdDate, String text, String userName) {
        this.id = id;
        this.postId = postId;
        this.createdDate = createdDate;
        this.text = text;
        this.userName = userName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CommentsDto id(Long id) {
        setId(id);
        return this;
    }

    public CommentsDto postId(Long postId) {
        setPostId(postId);
        return this;
    }

    public CommentsDto createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public CommentsDto text(String text) {
        setText(text);
        return this;
    }

    public CommentsDto userName(String userName) {
        setUserName(userName);
        return this;
    }
}