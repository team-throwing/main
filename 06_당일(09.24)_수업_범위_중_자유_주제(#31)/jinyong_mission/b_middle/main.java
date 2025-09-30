package com.ohgiraffers.mission.b_middle;

public class main {

    public static void main(String[] args) {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        UserToUserDtoConverter userToUserDtoConverter = new UserToUserDtoConverter();
        User user = new User();


        Integer aClass = stringToIntegerConverter.convert("1");
        UserDto aClass1 = userToUserDtoConverter.convert(user);

        System.out.println(aClass.getClass());
        System.out.println(aClass1.getClass());
    }
}