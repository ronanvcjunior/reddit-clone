package com.ronan.redditclone.controller;

import com.ronan.redditclone.dto.VoteDto;
import com.ronan.redditclone.service.VoteService;

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
@RequestMapping("/api/votes")
public class VoteController {
    
    private final VoteService service;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        service.vote(voteDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<VoteDto> findTopByPostAndUserOrderByVoteIdDesc(@PathVariable Long postId) {
        VoteDto voteResponse = service.findTopByPostAndUserOrderByVoteIdDesc(postId);
        return ResponseEntity.ok().body(voteResponse);
    }
}
