package com.ronan.redditclone.mapper;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.request.PostRequest;
import com.ronan.redditclone.dto.response.PostResponse;
import com.ronan.redditclone.repository.CommentRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post mapRequestToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    public abstract PostResponse mapPostToResponse(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }
}
