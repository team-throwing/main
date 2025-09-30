package com.ohgiraffers.mission.b_middle;

public class StringToIntegerConverter implements Converter<String,Integer> {

    @Override
    public Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
