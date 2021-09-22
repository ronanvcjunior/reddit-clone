package com.ronan.redditclone.dto;

public class PostRequest {
    private Long postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;

    public PostRequest() {
    }

    public PostRequest(Long postId, String subredditName, String postName, String url, String description) {
        this.postId = postId;
        this.subredditName = subredditName;
        this.postName = postName;
        this.url = url;
        this.description = description;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getSubredditName() {
        return this.subredditName;
    }

    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
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

    public PostRequest postId(Long postId) {
        setPostId(postId);
        return this;
    }

    public PostRequest subredditName(String subredditName) {
        setSubredditName(subredditName);
        return this;
    }

    public PostRequest postName(String postName) {
        setPostName(postName);
        return this;
    }

    public PostRequest url(String url) {
        setUrl(url);
        return this;
    }

    public PostRequest description(String description) {
        setDescription(description);
        return this;
    }
}