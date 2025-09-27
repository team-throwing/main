package com.ohgiraffers.mission.b_middle;

public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        return new UserDto(user.getName());
    }
}
