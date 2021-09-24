package com.ronan.redditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.dto.SubredditDto;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.mapper.SubredditMapper;
import com.ronan.redditclone.repository.SubredditRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository repository;

    private final AuthService authService;
    
    private final SubredditMapper mapper;
    
    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = mapper.mapDtoToSubreddit(subredditDto, authService.getCurrentUser());
        Subreddit save = repository.save(subreddit);
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly =  true)
    public List<SubredditDto> findAll() {
        List<Subreddit> subreddits = repository.findAll();
        List<SubredditDto> subredditDtos = subreddits.stream().map(mapper::mapSubredditToDto).collect(Collectors.toList());
        return subredditDtos;
    }

    @Transactional(readOnly = true)
    public SubredditDto findById(Long id) {
        Subreddit subreddit = repository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        SubredditDto subredditDto = mapper.mapSubredditToDto(subreddit);
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public SubredditDto findByName(String name) {
        Subreddit subreddit = repository.findByName(name)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with name - " + name));
        SubredditDto subredditDto = mapper.mapSubredditToDto(subreddit);
        return subredditDto;
    }
}
