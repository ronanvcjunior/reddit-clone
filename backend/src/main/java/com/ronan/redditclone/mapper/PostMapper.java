package com.ronan.redditclone.mapper;

import com.ronan.redditclone.domain.Post;
import com.ronan.redditclone.dto.request.PostRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    
    Post mapRequestToPost(PostRequest postRequest);
}
