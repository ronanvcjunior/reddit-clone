package com.ronan.redditclone.service;

import java.util.Optional;

import javax.transaction.Transactional;

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
import lombok.extern.log4j.Log4j2;

import static com.ronan.redditclone.domain.VoteType.UPVOTE;

@Log4j2
@Service
@AllArgsConstructor
public class VoteService {
    
    private final VoteRepository repository;

    private final PostRepository postRepository;

    private final AuthService authService;

    private final VoteMapper mapper;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser = repository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent()) {
            if (checkUserHasAlreadyVotedForPost(post, voteByPostAndUser, voteDto)) {
                deleteVote(voteByPostAndUser.get().getVoteId(), post);
                return;
            }
        } else {
            post.setReactionsCount(post.getReactionsCount() + 1);
        }

        voteUpOrDown(voteDto, post);

        Vote vote = mapper.mapDtoToVote(voteDto, post, authService.getCurrentUser());
        if(voteByPostAndUser.isPresent()) {
            vote.setVoteId(voteByPostAndUser.get().getVoteId());
        }
        vote = repository.save(vote);
    }
    
    private boolean checkUserHasAlreadyVotedForPost(Post post, Optional<Vote> voteByPostAndUser, VoteDto voteDto) {
        if(voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            if (UPVOTE.equals(voteDto.getVoteType())) {
                post.setVoteCount(post.getVoteCount() - 1);
            } else {
                post.setVoteCount(post.getVoteCount() + 1);
            }
            return true;
        } else {
            if (UPVOTE.equals(voteDto.getVoteType())) {
                post.setVoteCount(post.getVoteCount() + 1);
            } else {
                post.setVoteCount(post.getVoteCount() - 1);
            }
            return false;
        }
    }
    
        private void deleteVote(Long voteId, Post post) {
            repository.deleteById(voteId);
            post.setReactionsCount(post.getReactionsCount() - 1);
        }
    
    private void voteUpOrDown(VoteDto voteDto, Post post) {
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
    }
}
