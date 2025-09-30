package com.ohgiraffers.mission.b_middle;

public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        return userDto;
    }
}
