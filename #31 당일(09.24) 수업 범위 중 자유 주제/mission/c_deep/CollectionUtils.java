package com.ohgiraffers.mission.c_deep;

import java.util.List;

public class CollectionUtils {

    // 제네릭 메서드 선언 1
    <N extends Number> double sum1(List<N> numbers) {
        double res = 0.0;
        for (N n : numbers) {
            // Wrapper 타입 간에는 암묵적 형 변환이 안 됨
            // res += (double) n; -> class java.lang.Integer cannot be cast to class java.lang.Double!!
            res += n.doubleValue();
        }
        return res;
    }

    // 제네릭 메서드 선언 2
    double sum2(List<? extends Number> numbers) {
        double res = 0.0;
        for (Number n : numbers) {
            // Wrapper 타입 간에는 암묵적 형 변환이 안 됨
            // res += (double) n; -> class java.lang.Integer cannot be cast to class java.lang.Double!!
            res += n.doubleValue();
        }
        return res;
    }

    // 제네릭 메서드는 static 선언 가능
    static void addIntegers(List<? super Integer> list, int n) {
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
    }
}
