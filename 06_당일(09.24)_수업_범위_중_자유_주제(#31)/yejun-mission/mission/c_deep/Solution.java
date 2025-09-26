package com.ohgiraffers.mission.c_deep;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        // 2-1 Integer
        List<Integer> want2SumInt = new ArrayList<Integer>();
        // 리스트에 값 넣기
        for (int i = 1; i <= 10; i++) {
            want2SumInt.add(i);
        }
        CollectionUtils collectionUtils = new CollectionUtils();
        System.out.println(collectionUtils.sum(want2SumInt));

        // 2-2 Double
        List<Double> want2DoubleSum = new ArrayList<Double>(Arrays.asList(1.2, 2.4, 3.9, 4.8));

        System.out.println(collectionUtils.sum(want2DoubleSum));

        System.out.println("Submit n : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 3
        List<Integer> listInt = new ArrayList<Integer>();
        System.out.println(collectionUtils.addIntegers(listInt, n));
        System.out.println("check ref type listInt change? : " + listInt);

        List<Number> listNum = new ArrayList<Number>();
        System.out.println(collectionUtils.addIntegers(listNum, n));
        System.out.println("check ref type listNum change? : " + listNum);

        List<Object> listObj = new ArrayList<Object>();
        System.out.println(collectionUtils.addIntegers(listObj, n));
        System.out.println("check ref type listObj change? : " + listObj);
    }
}


