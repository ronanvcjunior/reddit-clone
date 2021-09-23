package com.ronan.redditclone.controller;

import java.util.List;

import com.ronan.redditclone.dto.SubredditDto;
import com.ronan.redditclone.service.SubredditService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService service;
    
    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        SubredditDto save = service.save(subredditDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        List<SubredditDto> subredditDtos = service.getAll();
        return ResponseEntity.ok().body(subredditDtos);
    }
    
}
