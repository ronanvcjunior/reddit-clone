package com.ronan.redditclone.controller;

import com.ronan.redditclone.dto.CommentsDto;
import com.ronan.redditclone.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
