package com.ronan.redditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.domain.Subreddit;
import com.ronan.redditclone.dto.SubredditDto;
import com.ronan.redditclone.dto.response.SubredditResponse;
import com.ronan.redditclone.exception.SpringRedditException;
import com.ronan.redditclone.mapper.SubredditMapper;
import com.ronan.redditclone.repository.SubredditRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        repository.save(subreddit);
        subredditDto = mapper.mapSubredditToDto(subreddit);
        return subredditDto;
    }

    @Transactional(readOnly =  true)
    public List<SubredditDto> findAll() {
        List<Subreddit> subreddits = repository.findAll();
        List<SubredditDto> subredditDtos = subreddits.stream().map(mapper::mapSubredditToDto).collect(Collectors.toList());
        return subredditDtos;
    }

    @Transactional(readOnly = true)
    public Page<SubredditDto> findAllPage(Pageable pageable) {
        Page<Subreddit> subredditsPage = repository.findAll(pageable);
        Page<SubredditDto> subredditsDtoPage = subredditsPage.map(mapper::mapSubredditToDto);
        return subredditsDtoPage;
    }

    @Transactional(readOnly = true)
    public SubredditDto findById(Long id) {
        Subreddit subreddit = repository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        SubredditDto subredditDto = mapper.mapSubredditToDto(subreddit);
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public SubredditResponse findByName(String name) {
        Subreddit subreddit = repository.findByName(name)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with name - " + name));
        SubredditResponse SubredditResponse = mapper.mapSubredditToResponse(subreddit);
        return SubredditResponse;
    }

    @Transactional
    public void updateListPostBySubredditName(Post post, String name) {
        Subreddit subreddit = repository.findByName(name)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with name - " + name));
        subreddit.deletePost(post);
        subreddit.setNumberOfPosts(subreddit.getNumberOfPosts()-1);
        repository.save(subreddit);
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> findByFirstLater(char letter) {
        List<Subreddit> subreddits;
        if (letter != '0') {
            subreddits = repository.findAllByFirstLetter(letter);
        } else {
            subreddits = repository.findAllByFirstLetterIsSpecialCharacter(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        }
        List<SubredditDto> subredditDtos = subreddits.stream().map(mapper::mapSubredditToDto).collect(Collectors.toList());
        return subredditDtos;
    }
}
