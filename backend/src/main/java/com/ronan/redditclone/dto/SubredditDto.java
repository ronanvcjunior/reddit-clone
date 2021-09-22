package com.ronan.redditclone.dto;

public class SubredditDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;

    public SubredditDto() {
    }

    public SubredditDto(Long id, String name, String description, Integer numberOfPosts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfPosts = numberOfPosts;
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

    public Integer getNumberOfPosts() {
        return this.numberOfPosts;
    }

    public void setNumberOfPosts(Integer numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public SubredditDto id(Long id) {
        setId(id);
        return this;
    }

    public SubredditDto name(String name) {
        setName(name);
        return this;
    }

    public SubredditDto description(String description) {
        setDescription(description);
        return this;
    }

    public SubredditDto numberOfPosts(Integer numberOfPosts) {
        setNumberOfPosts(numberOfPosts);
        return this;
    }
}