package com.ronan.redditclone.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.SubredditDto;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    SubredditDto mapSubredditToDto(Subreddit subreddit);

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "numberOfPosts", constant = "0")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto, User user);
}