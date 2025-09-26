package com.ohgiraffers.mission.b_middle.convert;

public interface Converter<T, R> {
    R convert(T t);
}
