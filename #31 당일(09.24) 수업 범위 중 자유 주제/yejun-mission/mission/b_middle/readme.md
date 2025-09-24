### **[중급] Intermediate: 데이터 변환기 만들기 (제네릭 인터페이스)**

-   **목표**: 제네릭을 인터페이스에 적용하여, 다양한 타입을 지원하면서도 타입 안전성을 보장하는 유연한 컴포넌트를 설계합니다.
-   **요구사항**:
    1.  `<T, R> R convert(T t)` 메서드를 가진 제네릭 인터페이스 `Converter<T, R>`를 정의합니다. (T: 입력 타입, R: 반환 타입)
    2.  `String`을 `Integer`로 변환하는 `StringToIntegerConverter` 클래스를 작성하여 `Converter<String, Integer>`를 구현합니다. `Integer.parseInt()`를 사용하세요.
    3.  간단한 `User` 클래스(필드: `id`, `name`)와 `UserDto` 클래스(필드: `name`)를 정의합니다.
    4.  `User` 객체를 `UserDto` 객체로 변환하는 `UserToUserDtoConverter` 클래스를 작성하여 `Converter<User, UserDto>`를 구현합니다.
    5.  `main` 메서드에서 각 변환기 객체를 사용하여, 타입 변환이 컴파일 시점에 안전하게 일어나는 것을 보입니다.