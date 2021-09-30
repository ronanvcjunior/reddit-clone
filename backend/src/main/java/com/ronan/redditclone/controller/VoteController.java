package com.ronan.redditclone.controller;

import com.ronan.redditclone.dto.VoteDto;
import com.ronan.redditclone.service.VoteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {
    
    private final VoteService service;

    @PostMapping
    public ResponseEntity<VoteDto> vote(@RequestBody VoteDto voteDto) {
        VoteDto vote = service.vote(voteDto);
        return ResponseEntity.ok().body(vote);
    }
}
