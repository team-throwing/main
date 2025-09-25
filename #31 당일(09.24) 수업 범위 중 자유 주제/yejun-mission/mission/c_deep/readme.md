### **[심화] Advanced: 컬렉션 유틸리티 만들기 (와일드카드 활용)**

-   **목표**: 제네릭 와일드카드를 사용하여 다양한 타입의 컬렉션을 안전하게 처리하는 범용 유틸리티 메서드를 작성하며 PECS 원칙을 체득합니다.
-   **요구사항**:
    1.  `CollectionUtils`라는 유틸리티 클래스를 만듭니다.
    2.  **`sum(List<? extends Number> numbers)`** 메서드를 작성합니다.
        -   `Number` 또는 그 자식 클래(`Integer`, `Double` 등)의 리스트를 받아 모든 요소의 합계를 `double`로 반환합니다. (**Producer Extends**)
        -   `main`에서 `List<Integer>`와 `List<Double>`을 각각 만들어 `sum` 메서드로 합계를 구하는 것을 테스트합니다.
    3.  **`addIntegers(List<? super Integer> list, int n)`** 메서드를 작성합니다.
        -   `Integer` 또는 그 부모 클래스(`Number`, `Object`)의 리스트를 받아, 1부터 `n`까지의 정수를 리스트에 추가합니다. (**Consumer Super**)
        -   `main`에서 `List<Integer>`, `List<Number>`, `List<Object>`를 각각 만들어 `addIntegers` 메서드를 호출하고 결과를 확인합니다.