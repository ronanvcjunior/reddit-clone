package com.ronan.redditclone.service;

import java.util.Optional;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Vote;
import com.ronan.redditclone.dto.VoteDto;
import com.ronan.redditclone.exception.PostNotFoundException;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.mapper.VoteMapper;
import com.ronan.redditclone.repository.PostRepository;
import com.ronan.redditclone.repository.VoteRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import static com.ronan.redditclone.domain.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {
    
    private final VoteRepository repository;

    private final PostRepository postRepository;

    private final AuthService authService;

    private final VoteMapper mapper;

    public VoteDto vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser = repository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        checkUserHasAlreadyVotedFotPost(post, voteByPostAndUser, voteDto);

        voteUpOrDown(voteDto, post);

        Vote vote = mapper.mapDtoToVote(voteDto, post, authService.getCurrentUser());
        vote = repository.save(vote);
        
        return mapper.mapVoteToDto(vote);
    }

    private void checkUserHasAlreadyVotedFotPost(Post post, Optional<Vote> voteByPostAndUser, VoteDto voteDto) {
        if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        }
    }

    private void voteUpOrDown(VoteDto voteDto, Post post) {
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
    }
}