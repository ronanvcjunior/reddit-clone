package com.ronan.redditclone.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
}