# Java 제네릭 정리

## 1. 제네릭이란?

### 1.1 정의와 등장 배경
**정의**: 클래스나 메서드에서 사용할 타입을 파라미터로 받아서, 실제 사용할 때 구체적인 타입을 지정하는 기능

**나온 이유**: 
- Java 5 이전에는 `Object` 타입으로 모든 것을 받아서 **타입 안전성** 부족
- 런타임에 `ClassCastException` 발생 위험
- 코드의 가독성과 유지보수성 저하

```java
// 제네릭 없이 (Java 5 이전)
List list = new ArrayList();
list.add("hello");
String str = (String) list.get(0); // 강제 캐스팅 필요, 위험!

// 제네릭 사용 (Java 5 이후)
List<String> list = new ArrayList<>();
list.add("hello");
String str = list.get(0); // 안전하고 명확!
```

### 1.2 자바 제네릭의 철학
- **타입 안전성**: 컴파일 타임에 타입 체크
- **하위 호환성**: 기존 코드와의 호환성 유지
- **타입 소거**: 런타임에는 원시 타입으로 변환 (성능 최적화)

### 1.3 타입 소거와 힙 오염
**타입 소거**: 컴파일 시 제네릭 타입 정보가 사라져서 런타임에는 `Object` 기반으로 동작

```java
// 컴파일 전
List<String> list = new ArrayList<>();

// 컴파일 후 (바이트코드)
List list = new ArrayList(); // 타입 정보 사라짐
```

**힙 오염**: 런타임에 잘못된 타입이 들어가서 발생하는 문제

```java
List<String> strings = new ArrayList<>();
List rawList = strings; // 타입 소거로 인한 raw 타입 사용
rawList.add(42); // Integer를 String 리스트에 추가!
String str = strings.get(0); // ClassCastException!
```

### 1.4 제네릭 vs ArrayList 등 컬렉션
- **ArrayList는 제네릭보다 먼저 등장** (Java 1.2)
- Java 5에서 제네릭이 도입되면서 기존 컬렉션들이 제네릭을 지원하도록 개선
- 하위 호환성을 위해 raw 타입도 여전히 사용 가능

### 1.5 JVM 레벨에서의 제네릭 처리

제네릭은 컴파일 타임에만 존재하고, JVM 레벨에서는 다음과 같이 처리됩니다:

| 구분 | 제네릭 정의 시 | 타입 지정 사용 시 |
|------|----------------|-------------------|
| **JVM 구조** | Class(메서드영역)에 코드 1개만 탑재 | 여전히 코드 1개 유지 |
| **바이트코드(런타임)** | 타입 변수(T 등) 존재해도 raw type | 타입 소거 후 Object 기반 |
| **메모리(Heap)** | 실제 데이터 없음(정의만) | 생성할 때 Heap에 객체 별도 동적 할당 |
| **타입 정보** | 컴파일 때만 체크, 런타임에는 없음 | 컴파일 때만 체크, 런타임에는 없음 |
| **객체 구조** | 없음(정의만) | 타입별 객체 각각, 실제 값 저장 |

**핵심**: 자바에서 제네릭은 컴파일 타임에만 타입을 체크해주는 문법이고, 실제로 프로그램이 실행될 때(JVM, 런타임)에는 타입 정보가 모두 사라져서 모든 제네릭 타입이 `Object`(혹은 타입 제한이 있으면 그 타입)로 변환되어 처리됩니다.

즉,
- **컴파일 시**: 타입 안정성 검사를 해서 실수 방지
- **런타임**: 타입 정보가 없어져서 Object처럼 다뤄짐(타입 소거)

이 덕분에 과거 코드와 호환성을 유지하면서, 코드 작성 시 타입 실수도 막을 수 있습니다.

## 2. 제네릭 사용법

### 2.1 제네릭 클래스 정의와 사용
```java
// 정의: T는 타입 파라미터 (Type Parameter)
class Box<T> { 
    private T value; 
    
    public void set(T value) { 
        this.value = value; 
    } 
    
    public T get() { 
        return value; 
    } 
}

// 사용: String이 타입 인자 (Type Argument)
Box<String> stringBox = new Box<>();
stringBox.set("hello");
String str = stringBox.get(); // 타입 안전!
```

**핵심**: `T`는 실제로 `String` 클래스가 들어가는 게 아니라, "String 타입을 사용하겠다"는 **약속**입니다.

### 2.2 타입 추론과 다이아몬드 연산자
```java
// 명시적 타입 지정
Box<String> box1 = new Box<String>();

// 타입 추론 (다이아몬드 연산자 <>)
Box<String> box2 = new Box<>(); // 컴파일러가 String으로 추론

// 제네릭 메서드의 타입 추론
public static <T> T getFirst(List<T> list) {
    return list.get(0);
}

// 호출 시 타입 추론
String first = getFirst(List.of("a", "b", "c")); // T = String으로 추론
Integer num = getFirst(List.of(1, 2, 3));        // T = Integer로 추론
```

## 3. 제네릭의 제약과 해결책

### 3.1 불변성(Invariance) 문제
**문제**: `List<String>`은 `List<Object>`의 하위타입이 아님

```java
List<String> strings = new ArrayList<>();
List<Object> objects = strings; // 컴파일 에러!
```

**해결**: 와일드카드 사용

### 3.2 와일드카드와 PECS 원칙
**PECS**: Producer Extends, Consumer Super

```java
// 읽기 위주 (Producer) - ? extends
void readNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) {
        System.out.println(num.doubleValue()); // Number의 메서드 사용 가능
    }
    // numbers.add(1); // 컴파일 에러! 어떤 타입인지 모르므로 추가 불가
}

// 쓰기 위주 (Consumer) - ? super  
void addIntegers(List<? super Integer> numbers) {
    numbers.add(1); // Integer는 확실히 추가 가능
    numbers.add(2);
    // Integer num = numbers.get(0); // 컴파일 에러! Object만 확실
}
```

**왜 이렇게 설계했을까?**
- **읽기**: 최상위 타입으로 받아야 모든 하위 타입을 처리 가능
- **쓰기**: 최하위 타입으로 받아야 안전하게 추가 가능

### 3.3 오버로드 충돌 문제
**문제**: 타입 소거로 인해 같은 메서드 시그니처가 됨

```java
// 이런 메서드들은 컴파일 에러!
public void print(List<String> list) { }
public void print(List<Integer> list) { } // 충돌!
```

**해결**: 메서드 이름을 다르게 하거나 다른 방식 사용

## 4. 제네릭과 프리미티브 타입

### 4.1 제네릭 타입 제약
- **제네릭 타입 인수는 참조 타입만 가능**
- `List<int>` 불가 → `List<Integer>` 사용

### 4.2 오토박싱/언박싱
```java
List<Integer> numbers = new ArrayList<>();
numbers.add(42); // int → Integer 자동 변환 (오토박싱)

int value = numbers.get(0); // Integer → int 자동 변환 (언박싱)
```

**성능 고려사항**:
- `primitive ↔ primitive`: 가장 빠름 (스택에서 직접 처리)
- `primitive ↔ wrapper`: 박싱/언박싱 오버헤드 발생

### 4.3 primitive 타입의 클래스 정보
```java
// primitive 값 자체는 객체가 아니므로 getClass() 불가
int value = 42;
// value.getClass(); // 컴파일 에러!

// 클래스 리터럴은 존재
Class<?> intClass = int.class;
Class<?> integerClass = Integer.class;
System.out.println(Integer.TYPE == int.class); // true
```

## 5. 제네릭 제약사항과 해결방법

### 5.1 타입 소거로 인한 제약들

**1. instanceof 연산자 사용 불가**
```java
List<String> list = new ArrayList<>();
// if (list instanceof List<String>) { } // 컴파일 에러!
if (list instanceof List) { } // 가능하지만 의미 없음
```

**2. 배열 생성 불가**
```java
// T[] array = new T[10]; // 컴파일 에러!
// List<String>[] lists = new List<String>[10]; // 컴파일 에러!
```

**이유**: 배열은 연속된 메모리 공간이 필요하고, 제네릭은 타입이 런타임에 없어서 크기를 미리 알 수 없음

### 5.2 해결 방법들

**1. 제네릭 배열 대신 List 사용**
```java
// 배열 대신
List<List<String>> lists = new ArrayList<>();
```

**2. 타입 토큰 사용**
```java
public class GenericClass<T> {
    private Class<T> type;
    
    public GenericClass(Class<T> type) {
        this.type = type;
    }
    
    public T createInstance() throws Exception {
        return type.newInstance();
    }
}
```

## 6. 실무 활용 팁

### 6.1 제네릭 사용 가이드라인
- 타입 안전성을 위해 제네릭 적극 활용
- 와일드카드로 유연성 확보
- PECS 원칙 준수

### 6.2 성능 고려사항
- 프리미티브 타입 사용 시 박싱 비용 고려
- 제네릭 컬렉션의 메모리 오버헤드
- 타입 소거로 인한 런타임 오버헤드 최소화

### 6.3 자주 사용하는 패턴
```java
// 제네릭 메서드
public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
    return list.stream()
               .filter(predicate)
               .collect(Collectors.toList());
}

// 제네릭 클래스
public class Repository<T, ID> {
    public T findById(ID id) { /* ... */ }
    public void save(T entity) { /* ... */ }
}
```

---

**핵심 정리**:
1. 제네릭은 타입 안전성을 위한 컴파일 타임 기능
2. 런타임에는 **타입 소거**로 인해 Object 기반으로 동작
3. 와일드카드로 유연성 확보 (PECS 원칙)
4. 프리미티브 타입은 래퍼 클래스로 사용
5. 배열 대신 List 사용 권장