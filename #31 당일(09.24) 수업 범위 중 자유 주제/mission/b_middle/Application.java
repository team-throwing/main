package com.ohgiraffers.mission.b_middle;

public class Application {

    public static void main(String[] args) {

        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        UserToUserDtoConverter userToUserDtoConverter = new UserToUserDtoConverter();

        try {
            // -2147483648 ~ 2147483647
            int converted = stringToIntegerConverter.convert("-2147483648");
            System.out.println(converted);
        } catch (NumberFormatException nfe) {
            System.err.println("문자열 변환 실패");
        }

        UserDto userDto = userToUserDtoConverter.convert(new User(1L, "Mike"));
        System.out.println(userDto);
    }
}
