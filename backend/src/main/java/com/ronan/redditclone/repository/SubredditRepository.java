package com.ronan.redditclone.repository;

import java.util.Optional;

import com.ronan.redditclone.domain.Subreddit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    
    Optional<Subreddit> findByName(String name);
}
