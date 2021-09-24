package com.ronan.redditclone.controller;

import java.util.List;

import com.ronan.redditclone.dto.CommentsDto;
import com.ronan.redditclone.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    
    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto) {
        commentsDto = service.save(commentsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsDto);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        List<CommentsDto> commentsDtos = service.getAllCommentsForPost(postId);
        return ResponseEntity.ok().body(commentsDtos);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String username) {
        List<CommentsDto> commentsDtos = service.getAllCommentsForUser(username);
        return ResponseEntity.ok().body(commentsDtos);
    }
}
