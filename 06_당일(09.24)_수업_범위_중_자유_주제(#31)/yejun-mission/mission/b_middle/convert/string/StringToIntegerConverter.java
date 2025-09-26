package com.ohgiraffers.mission.b_middle.convert.string;

import com.ohgiraffers.mission.b_middle.convert.Converter;

public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
