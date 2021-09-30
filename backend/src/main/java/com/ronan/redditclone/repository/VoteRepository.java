package com.ronan.redditclone.repository;

import java.util.Optional;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.domain.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
