package com.ronan.redditclone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ronan.redditclone.domain.Comment;
import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findAllByPost(Post post);
    
    Page<Comment> findAllByPost(Post post, Pageable pageable);

    List<Comment> findAllByUser(User user);

    Page<Comment> findAllByUser(User user, Pageable pageable);
}