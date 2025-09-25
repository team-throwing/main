package com.ohgiraffers.mission.b_middle;

public class StringToIntegerConverter implements Converter<String, Integer> {

    /**
     * 문자열 -> 정수
     * @param s 문자열
     * @throws NumberFormatException s 가 정수가 아니거나, 정수 표현 범위 -2147483648 ~ 2147483647 를 벗어난 경우
     * @return
     */
    @Override
    public Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
