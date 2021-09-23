package com.ronan.redditclone.service;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.dto.request.PostRequest;
import com.ronan.redditclone.mapper.PostMapper;
import com.ronan.redditclone.repository.PostRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    
    private final PostRepository repository;

    private final PostMapper mapper;

    public Post save(PostRequest postRequest) {
        Post post = mapper.mapRequestToPost(postRequest);
        post = repository.save(post);
        return post;
    }
}
