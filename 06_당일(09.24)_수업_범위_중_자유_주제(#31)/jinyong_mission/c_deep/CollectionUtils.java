package com.ohgiraffers.mission.c_deep;

import java.util.List;

public class CollectionUtils {
    private double sum1 = 0;


    public void sum(List<? extends Number> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            sum1 += numbers.get(i).doubleValue();
        }
        System.out.println(sum1);
        sum1 = 0;
    }

    public static void addIntergers(List<? super Integer> list, int n) {
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
    }
}
