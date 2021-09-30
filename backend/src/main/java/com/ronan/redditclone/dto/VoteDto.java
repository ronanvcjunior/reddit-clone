package com.ronan.redditclone.dto;

import com.ronan.redditclone.domain.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    
    private VoteType voteType;
    private Long postId;
}
