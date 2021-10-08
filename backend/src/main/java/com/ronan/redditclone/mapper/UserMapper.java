package com.ronan.redditclone.mapper;

import com.ronan.redditclone.domain.User;
import com.ronan.redditclone.dto.UserDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapUserToDto(User user);
}
