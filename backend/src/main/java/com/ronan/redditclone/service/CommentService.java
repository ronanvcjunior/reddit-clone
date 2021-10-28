package com.ronan.redditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.redditclone.domain.Comment;
import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.CommentsDto;
import com.ronan.redditclone.exception.PostNotFoundException;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.mapper.CommentMapper;
import com.ronan.redditclone.repository.CommentRepository;
import com.ronan.redditclone.repository.PostRepository;
import com.ronan.redditclone.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    
    private final CommentRepository repository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final CommentMapper mapper;

    @Transactional
    public CommentsDto save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
                
        Comment comment = mapper.mapDtoToComment(commentsDto, post, authService.getCurrentUser());
        comment = repository.save(comment);
        CommentsDto save = mapper.mapCommentToDto(comment);
        post.setCommentCount(post.getCommentCount()+1);
        return save;
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("No subreddit found with ID - " + postId));

        List<Comment> comments = repository.findByPost(post);
        List<CommentsDto> commentsDtos = comments.stream().map(mapper::mapCommentToDto).collect(Collectors.toList());
        return commentsDtos;
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringRedditException("User not found with name - " + username));

        List<Comment> comments = repository.findAllByUser(user);
        List<CommentsDto> commentsDtos = comments.stream().map(mapper::mapCommentToDto).collect(Collectors.toList());
        return commentsDtos;
    }
}
