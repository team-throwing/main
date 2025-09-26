package com.ohgiraffers.mission.b_middle;

import com.ohgiraffers.mission.b_middle.convert.dto.User;
import com.ohgiraffers.mission.b_middle.convert.dto.User2UserDtoConverter;
import com.ohgiraffers.mission.b_middle.convert.dto.UserDto;
import com.ohgiraffers.mission.b_middle.convert.string.StringToIntegerConverter;

public class Solution {
    public static void main(String[] args) {
        StringToIntegerConverter converter = new StringToIntegerConverter();

        Integer afterValue = converter.convert("123");

        System.out.println("\"123\" converte to " + afterValue +
                ",type is " + afterValue.getClass().getSimpleName());

        User2UserDtoConverter user2UserDtoConverter = new User2UserDtoConverter();

        User beforeUser = new User(1,"ye");

        UserDto afterUser = user2UserDtoConverter.convert(beforeUser);

        System.out.println(beforeUser);
        System.out.println(afterUser);
    }
}


