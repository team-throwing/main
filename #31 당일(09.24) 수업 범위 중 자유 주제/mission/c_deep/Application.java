package com.ohgiraffers.mission.c_deep;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Application {

    public static void main(String[] args) {

        /*
            [2]
         */
        List<Integer> integerList = List.of(1, 2, 3);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);
        CollectionUtils collectionUtils = new CollectionUtils();

        System.out.println("collectionUtils.sum1(integerList) = " + collectionUtils.sum1(integerList));
        System.out.println("collectionUtils.sum1(doubleList) = " + collectionUtils.sum1(doubleList));

        System.out.println("collectionUtils.sum2(integerList) = " + collectionUtils.sum2(integerList));
        System.out.println("collectionUtils.sum2(doubleList) = " + collectionUtils.sum2(doubleList));

        /*
            [3]
         */
        List<Integer> integers = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        CollectionUtils.addIntegers(integers, 10);
        CollectionUtils.addIntegers(numbers, 10);
        CollectionUtils.addIntegers(objects, 10);
        System.out.println("\n[integers]");
        integers.forEach(i -> {
            System.out.print(i + ", ");
        });
        System.out.println("\n[numbers]");
        numbers.forEach(n -> {
            System.out.print(n + ", ");
        });
        System.out.println("\n[objects]");
        objects.forEach(o -> {
            System.out.print(o + ", ");
        });
    }
}
