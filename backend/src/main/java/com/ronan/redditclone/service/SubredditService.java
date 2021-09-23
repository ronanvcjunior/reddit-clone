package com.ronan.redditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.dto.SubredditDto;
import com.ronan.redditclone.repository.SubredditRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository repository;
    
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = mapSubredditDto(subredditDto);
        Subreddit save = repository.save(subreddit);
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        Subreddit subreddit = new Subreddit();
        subreddit.setName(subredditDto.getName());
        subreddit.setDescription(subredditDto.getDescription());

        return subreddit;
    }

    public List<SubredditDto> getAll() {
        List<SubredditDto> subredditDtos = repository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
        return subredditDtos;
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        SubredditDto subredditDto = new SubredditDto();
        subredditDto.setId(subreddit.getId());
        subredditDto.setName(subreddit.getName());
        subredditDto.setDescription(subreddit.getDescription());
        subredditDto.setNumberOfPosts(subreddit.getPosts().size());

        return subredditDto;
    }
}
