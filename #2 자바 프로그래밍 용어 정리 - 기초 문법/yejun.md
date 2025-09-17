# 자바 프로그래밍 용어 정리 - 기초 문법

### 자바 기초 용어

#### JVM, JRE, JDK
- **JVM (Java Virtual Machine)** 
    - 운영체제에 독립적이며, 자바 바이트코드를 실행
    - 운영체제가 인식하지 못하는 자바 기반의 프로그램을 실행할 수 있도록 도와줌
    - 세부적으로 Class Loader, Runtime Data Area, Execution Engine 등으로 구성
        - Class Loader : Read Java 바이트코드 -> Load runtime data area -> Linking(검증, 준비, 해석) -> Initialization
        - Runtime Data Area : JVM이 프로그램 실행 시 사용하는 메모리 공간 / Thread individual area & Thread shared area 
            - Thread가 공유하는 영역 : Method Area, Heap Area   
            - Thread마다 독립적인 영역 : PC Register, JVM Stack, Native Method Stack
        - Execution Engine : method area에서 runtime data area에 로드된 bytecode 기계어로 변환 & 실행
            - Interpreter : 바이트코드를 한 줄씩 읽어 해석 & 실행
            - JIT(Just-In-Time) Compiler : 자주 사용되는 바이트코드를 기계어로 변환하여 캐시에 저장 -> 이후 동일한 바이트코드 실행 시 캐시된 기계어 사용 -> 성능 향상
            - Garbage Collector : 더 이상 참조되지 않는 객체 메모리 해제
 
- **JRE (Java Runtime Environment)**: JVM + 라이브러리 + 기타 파일

- **JDK (Java Development Kit)** : JRE + Development Tools (컴파일러, 디버거 등)
    - Development Tools
        - Java Compiler (javac) : .java -> .class (바이트코드)
        - Java Launcher (java) : .class 실행
        - Java Archive Tool (jar) : 여러 .class 파일을 하나의 .jar 파일로 묶음
        - Java Debugger (jdb) : 디버깅 도구
        - Java Documentation Generator (javadoc) : 주석 기반 API 문서 생성

#### OOP (Object-Oriented Programming) 
- **클래스(Class)** : 객체를 생성하기 위해 정의해놓은 설계도
  - 보통 두가지의 주된 요소로 이루어짐 : properties(속성) + methods(행동)

- **객체(Object)** : 클래스기반으로 생성된 인스턴스
  - class의 속성을 인스턴스는 각각의 value로 가짐

- **메서드(Method)** : 클래스가 수행할 수 있는 행동(함수)

> 추가로 알면 좋은 개념
> **접근제어자** : public, private, protected, default
> **생성자** : 객체 생성 시 호출되는 특수 메서드
  
```java
// 예시 코드
class Car {
    // 속성 (properties)
    String color;
    String model;
    int year;

    // 행동 (methods)
    void start() {
        System.out.println("Car is starting");
    }

    void stop() {
        System.out.println("Car is stopping");
    }
}
Car myCar = new Car(); // 객체 생성
myCar.color = "Red"; // 속성 값 설정
myCar.start(); // 메서드 호출
```

#### 자바 기초 문법 용어
- **변수(Variable)** : 값을 저장하는 메모리 공간
  - Declaration(선언) : 데이터 타입 + 변수 이름
  - Initialization(초기화) : 변수에 처음 값을 할당
  - Assignment(할당) : 변수에 값을 할당 또는 변경
  - Scope(범위) : 변수의 유효 범위 (지역변수, 전역변수 등)
- **자료형(Data Type)** : Variable 의 값의 종류
  - Primitive Data Types (기본 자료형) : int, double, char, boolean 등
  - Reference Data Types (참조 자료형) : String, Arrays, Objects 등
- **연산자(Operator)** : Variable 등을 제어 하기 위한 기호
  - 산술 연산자 : +, -, *, /, %
  - 비교 연산자 : ==, !=, >, <, >=, <=
  - 논리 연산자 : &&, ||, !
  - 대입 연산자 : =, +=, -=, *=, /=
  - 비트 연산자 : &, |, ^, ~, <<, >>, >>>
  - 삼항 연산자 : 조건 ? 참일 때 값 : 거짓일 때 값
  - 증감 연산자 : ++, --
- **제어문(Control Statement)** :  상황에 따른 코드의 흐름 제어
  - 조건문 : if, else if, else, switch
  - 반복문 : for, while, do-while
  - 기타 제어문 : break, continue, return

```java
// 예시 코드
int a = 10; // 변수 선언 및 초기화
int b; // 변수 선언
b = 20; // 변수 할당
int sum = a + b; // 산술 연산자 사용
if (a<< 1 == b) { // 조건문 사용
    System.out.println("a shifted left by 1 equals b");
}
```

#### package
- **패키지(Package)** : 자바 클래스 및 인터페이스를 그룹화하는 논리적 단위
  - 선언 : `package packageName;` (파일 최상단에 위치)
  - 목적 : 클래스 충돌 방지, 코드 조직화, 접근 제어
  - 기본 패키지 : `java.lang` (자동으로 import 됨)
  - 사용자 정의 패키지 : 개발자가 직접 생성 가능
- **import** : 다른 패키지의 클래스 등을 호출 할 때 사용
  - 선언 : `import packageName.ClassName;` 또는 `import packageName.*;`
  - static import : `import static packageName.ClassName.staticMember;` (static 멤버 직접 접근 가능)

```java
// 예시 코드 (import 사용 O)
package com.example.myapp; // 패키지 선언
import java.util.ArrayList; // 특정 클래스 import
import java.util.*; // 모든 클래스 import
import static java.lang.Math.PI; // static import
public class MyApp {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(); // java.util.ArrayList 사용
        System.out.println("Value of PI: " + PI); // Math.PI 직접 접근
    }
}

// 예시 코드 (import 사용 X)
// 같은 내용
public class MyApp {
    public static void main(String[] args) {
        java.util.ArrayList<String> list = new java.util.ArrayList<>(); // 전체 경로 사용
        System.out.println("Value of PI: " + java.lang.Math.PI); // 전체 경로 사용
    }
}
```