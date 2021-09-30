package com.ronan.redditclone.controller;

import java.util.List;

import com.ronan.redditclone.dto.request.PostRequest;
import com.ronan.redditclone.dto.response.PostResponse;
import com.ronan.redditclone.service.PostService;

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
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;
    
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse save = service.save(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> postDtos = service.findAll();
        return ResponseEntity.ok().body(postDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        PostResponse postResponse = service.findById(id);
        return ResponseEntity.ok().body(postResponse);
    }

    @GetMapping(path = "/by-subreddit/{idSubreddit}")
    public ResponseEntity<List<PostResponse>> getPostBySubreddit(@PathVariable Long idSubreddit) {
        List<PostResponse> postResponses = service.findPostBySubreddit(idSubreddit);
        return ResponseEntity.ok().body(postResponses);
    }

    @GetMapping(path = "/by-user/{username}")
    public ResponseEntity<List<PostResponse>> getPostByUser(@PathVariable String username) {
        List<PostResponse> postResponses = service.findPostByUsername(username);
        return ResponseEntity.ok().body(postResponses);
    }
}
