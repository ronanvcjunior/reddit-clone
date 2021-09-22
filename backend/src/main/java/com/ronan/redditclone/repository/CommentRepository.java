package com.ronan.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronan.redditclone.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}