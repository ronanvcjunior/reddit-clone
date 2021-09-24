package com.ronan.redditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.dto.request.PostRequest;
import com.ronan.redditclone.dto.response.PostResponse;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.exception.SubredditNotFoundException;
import com.ronan.redditclone.mapper.PostMapper;
import com.ronan.redditclone.repository.PostRepository;
import com.ronan.redditclone.repository.SubredditRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    
    private final PostRepository repository;

    private final SubredditRepository subredditRepository;

    private final AuthService authService;

    private final PostMapper mapper;

    @Transactional
    public Post save(PostRequest postRequest) {
        String subredditName = postRequest.getSubredditName();
        Subreddit subreddit = subredditRepository.findByName(subredditName)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with name - " + subredditName));
            
        Post post = mapper.mapRequestToPost(postRequest, subreddit, authService.getCurrentUser());
        post = repository.save(post);
        return post;
    }

    @Transactional(readOnly =  true)
    public List<PostResponse> findAll() {
        List<Post> posts = repository.findAll();
        List<PostResponse> postResponses = posts.stream().map(mapper::mapPostToResponse).collect(Collectors.toList());
        return postResponses;
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        PostResponse postResponse = mapper.mapPostToResponse(post);
        return postResponse;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findPostBySubreddit(Long idSubreddit) {
        Subreddit subreddit = subredditRepository.findById(idSubreddit)
                .orElseThrow(() -> new SubredditNotFoundException(idSubreddit.toString()));

        List<Post> posts = repository.findAllBySubreddit(subreddit);
        return posts.stream().map(mapper::mapPostToResponse).collect(Collectors.toList());
    }
}
