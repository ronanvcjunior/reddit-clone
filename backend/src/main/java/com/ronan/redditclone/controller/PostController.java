package com.ronan.redditclone.controller;

import java.util.List;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.dto.request.PostRequest;
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
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        Post save = service.save(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

//     @GetMapping
//     public ResponseEntity<List<Post>> getAllPosts() {
//         List<Post> postDtos = service.findAll();
//         return ResponseEntity.ok().body(postDtos);
//     }

//     @GetMapping(path = "/{id}")
//     public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//         Post postDto = service.findById(id);
//         return ResponseEntity.ok().body(postDto);
//     }
}
