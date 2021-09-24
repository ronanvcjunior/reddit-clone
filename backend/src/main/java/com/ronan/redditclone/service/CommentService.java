package com.ronan.redditclone.service;

import com.ronan.redditclone.domain.Comment;
import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.dto.CommentsDto;
import com.ronan.redditclone.exception.PostNotFoundException;
import com.ronan.redditclone.mapper.CommentMapper;
import com.ronan.redditclone.repository.CommentRepository;
import com.ronan.redditclone.repository.PostRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    
    private final CommentRepository repository;

    private final PostRepository postRepository;

    private final AuthService authService;

    private final CommentMapper mapper;

    public CommentsDto save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = mapper.mapDtoToComment(commentsDto, post, authService.getCurrentUser());
        comment = repository.save(comment);
        CommentsDto save = mapper.mapCommentToDto(comment);
        return save;
    }
}
