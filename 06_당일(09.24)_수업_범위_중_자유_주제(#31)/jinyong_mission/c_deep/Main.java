package com.ohgiraffers.mission.c_deep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        CollectionUtils collectionUtils = new CollectionUtils();

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double>  doubleList = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);

        collectionUtils.sum(integerList);
        collectionUtils.sum(doubleList);

        List<Integer> list = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<Number> list3 = new ArrayList<>();

        CollectionUtils.addIntergers(list, 5);
        CollectionUtils.addIntergers(list3, 10);
        CollectionUtils.addIntergers(list2, 4);

        System.out.println("list = " + list);
        System.out.println("list2 = " + list2);
        System.out.println("list3 = " + list3);
    }
}
