package com.ronan.redditclone.controller;

import java.util.List;

import com.ronan.redditclone.dto.SubredditDto;
import com.ronan.redditclone.service.SubredditService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
        List<SubredditDto> subredditDtos = service.findAll();
        return ResponseEntity.ok().body(subredditDtos);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SubredditDto>> getAllSubredditsPage(Pageable pageable) {
        Page<SubredditDto> subredditDtos = service.findAllPage(pageable);
        return ResponseEntity.ok().body(subredditDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable Long id) {
        SubredditDto subredditDto = service.findById(id);
        return ResponseEntity.ok().body(subredditDto);
    }

    @GetMapping(path = "/name/{nameSubreddit}")
    public ResponseEntity<SubredditDto> getSubredditByName(@PathVariable String nameSubreddit) {
        SubredditDto subredditDto = service.findByName(nameSubreddit);
        return ResponseEntity.ok().body(subredditDto);
    }
}
