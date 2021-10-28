package com.ronan.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.github.marlonlom.utilities.timeago.TimeAgoMessages;
import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.domain.Vote;
import com.ronan.redditclone.domain.VoteType;
import com.ronan.redditclone.dto.request.PostRequest;
import com.ronan.redditclone.dto.response.PostResponse;
import com.ronan.redditclone.repository.VoteRepository;
import com.ronan.redditclone.service.AuthService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

// import lombok.extern.log4j.Log4j2;

import java.util.Locale;
import java.util.Optional;

@Mapper(componentModel = "spring")
// @Log4j2
public abstract class PostMapper {

    @Autowired
    private AuthService authService;

    @Autowired
    private VoteRepository voteRepository;
    
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "commentCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post mapRequestToPost(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapPostToResponse(Post post);

    String getDuration(Post post) {
        Locale LocaleBylanguageTag = Locale.forLanguageTag("pt");
        TimeAgoMessages messages = new TimeAgoMessages.Builder().withLocale(LocaleBylanguageTag).build();
        // log.info(messages);
        // log.info(TimeAgo.using(post.getCreatedDate().toEpochMilli(), messages));
        return TimeAgo.using(post.getCreatedDate().toEpochMilli(), messages);
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, VoteType.UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, VoteType.DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                    authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
        }
        return false;
    }
}
