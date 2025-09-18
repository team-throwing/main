## 객체지향 프로그래밍(OOP) 4대 특성

객체지향 프로그래밍은 코드의 재사용성을 높이고 유지보수를 용이하게 만드는 네 가지 핵심적인 특징을 가집니다.
- [객체지향 프로그래밍(OOP) 4대 특성](#객체지향-프로그래밍oop-4대-특성)
    - [캡슐화 (Encapsulation)](#캡슐화-encapsulation)
    - [상속 (Inheritance)](#상속-inheritance)
    - [다형성 (Polymorphism)](#다형성-polymorphism)
    - [추상화 (Abstraction)](#추상화-abstraction)

#### 캡슐화 (Encapsulation)

  * 데이터(속성)와 데이터를 처리하는 메서드를 하나로 묶고, 외부의 직접적인 접근으로부터 데이터를 보호하는 것
  * 데이터의 무결성을 보장하고, 외부의 간섭을 최소화하여 객체의 독립성을 
  * If 캡슐화를 하지 않는다면, 외부에서 객체의 내부 데이터에 직접 접근하여 변경할 수 있어, 데이터 무결성이 깨질 수 있음. 
    * 예를 들어, 은행 계좌 객체의 잔액(balance) 필드가 외부에서 직접 수정 가능하다면, 잘못된 금액이 설정될 위험이 존재
  * **구현**:
      * 필드(변수)는 `private`으로 선언하여 외부 접근 배제
      * 외부에서 데이터에 경우, `public`으로 따로 **Getter/Setter** 와 같은 메서드를 제공
      * 

```java
// 예시 코드
class BankAccount {
    private double balance; // private으로 데이터 보호

    public double getBalance() { // Getter: 외부에서 잔액 조회
        return balance;
    }

    public void deposit(double amount) { // Setter 역할: 입금 기능
        if (amount > 0) {
            balance += amount;
        }
    }
}
// 사용 예시
BankAccount account = new BankAccount();
account.deposit(1000); // 입금
System.out.println("잔액: " + account.getBalance()); // 잔액 조회
```

-----

#### 상속 (Inheritance)

  * 기존 클래스(부모 클래스, Superclass)의 속성과 메서드를 새로운 클래스(자식 클래스, Subclass)가 물려받는 것
  * 코드의 **재사용성**을 높여 개발 효율 향상
  * `extends` 키워드를 사용하여 상속 구현
  * If 상속을 사용하지 않는다면, 공통된 기능이나 속성을 여러 클래스에 중복 작성해야 하므로 코드의 재사용성이 떨어지고, 유지보수가 어려워짐. 
    * 예를 들어, `Animal` 클래스를 상속받지 않고 `Dog`, `Cat` 클래스 각각에 `eat()`, `sleep()` 메서드를 별도로 구현한다면, 코드 중복이 발생하고, 기능 변경 시 모든 클래스에서 수정해야 하는 번거로움이 있음
  * 상속 관련 주요 개념
      * **오버라이딩 (Overriding)**
        * 부모 클래스로부터 상속받은 메서드를 자식 클래스에서 **재정의** 
        * 메서드의 이름, 매개변수, 반환 타입이 모두 동일해야 함 
        * 오버라이딩을 통해 다형성이 구현되는 것이지만, 오버라이딩 자체의 개념은 상속의 추가적인 기능이라고 보는게 맞는듯 함
        * `@Override` 어노테이션을 사용하여 명시적으로 표시

```java
// 예시 코드
class Animal { // 부모 클래스
    void cry() {
        System.out.println("동물이 웁니다.");
    }
}

class Dog extends Animal { // Animal 클래스를 상속받는 자식 클래스
    @Override // 오버라이딩: 부모의 cry() 메서드를 재정의
    void cry() {
        System.out.println("멍멍!");
    }
}
```

-----

#### 다형성 (Polymorphism)

  * 하나의 타입(인터페이스나 부모 클래스)으로 여러 다른 타입의 객체를 참조할 수 있는 성질. 즉, 여러 형태로 변형 가능
  * 코드의 유연성과 확장성을 올리고, 객체를 부품처럼 쉽게 교체 가능
  * 상속(오버라이딩)이나 인터페이스 구현을 통해 실현됩니다.
  * 즉, 타입을 명시하는 부분은 해당 변수로 들어올 객체가 해당 타입에 포함되면 어떤 객체든 들어올수 있고, 실제 객체는 해당 타입을 상속받은 더 자세한 자식 객체가 들어올수 있다. -> 변수의 명시부분은 타입가드 적인 요소로 느껴지고, 실제 객체는 사용의 목적에 맞도록
  * **관련 개념**:
      * **오버로딩 (Overloading)**: 한 클래스 내에서 **같은 이름의 메서드**를 여러 개 정의하는 것. 단, 매개변수의 **타입이나 개수**가 달라야 합니다.


```java
// 위 코드 이어서
// 예시 코드 (다형성) - 동적 다형성
class Cat extends Animal {
    @Override
    void cry() {
        System.out.println("야옹!");
    }
}

Animal myPet = new Dog(); // 부모 타입(Animal)으로 자식 객체(Dog)를 참조
myPet.cry(); // 실행 결과: "멍멍!" (오버라이딩된 메서드가 호출됨)
// myPet 변경 시,
myPet = new Cat();
myPet.cry(); // 실행 결과: "야옹!"


// 예시 코드 (오버로딩) - 정적 다형성
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    double add(double a, double b) { // 이름은 같지만 매개변수 타입이 다름
        return a + b;
    }
}
```

-----

#### 추상화 (Abstraction)

  * 객체의 공통적인 속성과 기능(핵심적인 부분)을 추출하여 모델링하고, 불필요한 세부 사항은 숨기는 것.
  * 복잡한 시스템을 단순화하여 이해하기 쉽게 만들고, 개발자가 핵심 기능에만 집중할 수 있도록 돕습니다.
  * **구현 방법**:
      * **추상 클래스 (Abstract Class)**: 하나 이상의 `abstract` 메서드(구현부가 없는 메서드)를 포함할 수 있는 클래스. **인스턴스화(객체 생성)가 불가능**하며, 상속을 통해서만 구현될 수 있습니다. (단일 상속만 가능)
      * **인터페이스 (Interface)**: 모든 메서드가 `abstract` 메서드(Java 8부터는 `default`, `static` 메서드 포함 가능)인 순수한 설계도. **다중 구현**이 가능하여 클래스에 여러 "역할"을 부여할 수 있습니다.

<!-- end list -->

```java
// 예시 코드 (추상 클래스)
abstract class Shape { // 추상 클래스
    abstract void draw(); // 추상 메서드 (구현부 없음)
    void info() { System.out.println("도형입니다."); }
}

class Circle extends Shape {
    @Override
    void draw() { System.out.println("원을 그립니다."); } // 추상 메서드 구현
}

// 예시 코드 (인터페이스)
interface Flyable {
    void fly(); // 인터페이스의 메서드는 기본적으로 public abstract
}

class Bird implements Flyable {
    @Override
    public void fly() { System.out.println("새가 하늘을 납니다."); } // 인터페이스 구현
}
```