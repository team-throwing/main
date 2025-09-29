package com.ohgiraffers.mission.b_middle;

public interface Converter<T, R> {

    /**
     * convert T into R
     */
    R convert(T t);
}
