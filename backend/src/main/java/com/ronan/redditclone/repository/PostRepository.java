package com.ronan.redditclone.repository;

import java.util.List;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    List<Post> findAllBySubreddit(Subreddit subreddit);
    
    Page<Post> findAllBySubreddit(Subreddit subreddit, Pageable pageable);

    List<Post> findAllByUser(User user);

    Page<Post> findAllByUser(User user, Pageable pageable);
}
