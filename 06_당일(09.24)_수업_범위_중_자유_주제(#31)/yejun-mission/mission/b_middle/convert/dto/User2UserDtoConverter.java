package com.ohgiraffers.mission.b_middle.convert.dto;

import com.ohgiraffers.mission.b_middle.convert.Converter;

public class User2UserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        String name = user.getName();
        return new UserDto(name);

    }
}
