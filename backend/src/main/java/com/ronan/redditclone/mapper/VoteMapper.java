package com.ronan.redditclone.mapper;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.domain.Vote;
import com.ronan.redditclone.dto.VoteDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Vote mapDtoToVote(VoteDto voteDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(vote.getPost().getPostId())")
    VoteDto mapVoteToDto(Vote vote);
}
