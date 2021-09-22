package com.ronan.redditclone.dto;

public class PostResponse {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private String userName;
    private String subredditName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
    private boolean upVote;
    private boolean downVote;

    public PostResponse() {
    }

    public PostResponse(Long id, String postName, String url, String description, String userName, String subredditName, Integer voteCount, Integer commentCount, String duration, boolean upVote, boolean downVote) {
        this.id = id;
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.userName = userName;
        this.subredditName = subredditName;
        this.voteCount = voteCount;
        this.commentCount = commentCount;
        this.duration = duration;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubredditName() {
        return this.subredditName;
    }

    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
    }

    public Integer getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isUpVote() {
        return this.upVote;
    }

    public boolean getUpVote() {
        return this.upVote;
    }

    public void setUpVote(boolean upVote) {
        this.upVote = upVote;
    }

    public boolean isDownVote() {
        return this.downVote;
    }

    public boolean getDownVote() {
        return this.downVote;
    }

    public void setDownVote(boolean downVote) {
        this.downVote = downVote;
    }

    public PostResponse id(Long id) {
        setId(id);
        return this;
    }

    public PostResponse postName(String postName) {
        setPostName(postName);
        return this;
    }

    public PostResponse url(String url) {
        setUrl(url);
        return this;
    }

    public PostResponse description(String description) {
        setDescription(description);
        return this;
    }

    public PostResponse userName(String userName) {
        setUserName(userName);
        return this;
    }

    public PostResponse subredditName(String subredditName) {
        setSubredditName(subredditName);
        return this;
    }

    public PostResponse voteCount(Integer voteCount) {
        setVoteCount(voteCount);
        return this;
    }

    public PostResponse commentCount(Integer commentCount) {
        setCommentCount(commentCount);
        return this;
    }

    public PostResponse duration(String duration) {
        setDuration(duration);
        return this;
    }

    public PostResponse upVote(boolean upVote) {
        setUpVote(upVote);
        return this;
    }

    public PostResponse downVote(boolean downVote) {
        setDownVote(downVote);
        return this;
    }
}