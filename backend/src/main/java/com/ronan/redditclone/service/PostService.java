package com.ronan.redditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.request.PostRequest;
import com.ronan.redditclone.dto.response.PostResponse;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.exception.SubredditNotFoundException;
import com.ronan.redditclone.mapper.PostMapper;
import com.ronan.redditclone.repository.PostRepository;
import com.ronan.redditclone.repository.SubredditRepository;
import com.ronan.redditclone.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    
    private final PostRepository repository;

    private final SubredditRepository subredditRepository;

    private final UserRepository userRepository;
    
    private final AuthService authService;
    
    private final PostMapper mapper;
    
    @Transactional
    public PostResponse save(PostRequest postRequest) {
        String subredditName = postRequest.getSubredditName();
        Subreddit subreddit = subredditRepository.findByName(subredditName)
        .orElseThrow(() -> new SpringRedditException("No subreddit found with name - " + subredditName));
        
        Post post = mapper.mapRequestToPost(postRequest, subreddit, authService.getCurrentUser());
        post = repository.save(post);
        
        PostResponse postResponse = mapper.mapPostToResponse(post);
        
        subreddit.getPosts().add(post);
        subreddit.setNumberOfPosts(subreddit.getNumberOfPosts()+1);
        return postResponse;
    }
    
    @Transactional(readOnly =  true)
    public List<PostResponse> findAll() {
        List<Post> posts = repository.findAll();
        List<PostResponse> postResponses = posts.stream().map(mapper::mapPostToResponse).collect(Collectors.toList());
        return postResponses;
    }
    
    @Transactional(readOnly = true)
    public Page<PostResponse> findAllPage(Pageable pageable) {
        Page<Post> postPage = repository.findAll(pageable);
        Page<PostResponse> postResponsesPage = postPage.map(mapper::mapPostToResponse);
        return postResponsesPage;
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
    
    @Transactional(readOnly = true)
    public Page<PostResponse> findPostPostBySubreddit(Long idSubreddit, Pageable pageable) {
        Subreddit subreddit = subredditRepository.findById(idSubreddit)
                .orElseThrow(() -> new SubredditNotFoundException(idSubreddit.toString()));
        
        Page<Post> postsPage = repository.findAllBySubreddit(subreddit, pageable);
        return postsPage.map(mapper::mapPostToResponse);
    }

    public List<PostResponse> findPostByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        List<Post> posts = repository.findAllByUser(user);
        return posts.stream().map(mapper::mapPostToResponse).collect(Collectors.toList());
    }

    public Page<PostResponse> findPostPageByUsername(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Page<Post> postsPage = repository.findAllByUser(user, pageable);
        return postsPage.map(mapper::mapPostToResponse);
    }
}
