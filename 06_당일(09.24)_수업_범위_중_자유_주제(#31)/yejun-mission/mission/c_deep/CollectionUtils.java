package com.ohgiraffers.mission.c_deep;

import java.math.BigDecimal;
import java.util.List;

class CollectionUtils {
    // 기본 연산
//    public double sum(List<? extends Number> numbers) {
//        double resultSum = 0;
//        for (Number n : numbers) {
//            resultSum += n.doubleValue();
//        }
//        return resultSum;
//    }
    // use Decimal -> 정확도 높이기
    public double sum(List<? extends Number> numbers) {
        BigDecimal resultSum = BigDecimal.ZERO;
        for (Number n : numbers) {
            resultSum = resultSum.add(new BigDecimal(n.toString()));
        }
        return resultSum.doubleValue();
    }


    public int addIntegers(List<? super Integer> list, int n) { // 문제가 잘 이해가 안감...?
        int result = 0;

        for (int i = 1; i <= n; i++) { // for-each
            list.add(i);
        }

        for (int i = 0; i < list.size(); i++) { // list 인덱싱도 연습용
            result += (int) ((Integer) list.get(i)); // Integer로 형변환만 해줘도 알아서 합산될거같지만? 그냥 의도가 이렇다 느낌이로 한번더?
        }

        return result;

    }
}
