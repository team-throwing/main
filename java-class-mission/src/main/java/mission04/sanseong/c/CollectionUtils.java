package mission04.sanseong.c;

import java.util.List;

public class CollectionUtils {
    public Double sum(List<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }

    public void addIntegers(List<? super Integer> numbers, Integer n) {
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
    }
}
