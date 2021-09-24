package com.ronan.redditclone.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}