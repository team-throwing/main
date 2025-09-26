# 📚 현대 개발 패턴 핵심 정리 (Day 4)

## 1. 프레젠테이션 레이어 패턴

### MVC vs MVP vs MVVM

**MVC (Model-View-Controller)**
- **철학**: 관심사 분리 - UI, 비즈니스 로직, 데이터 분리
- **특징**: View가 Model을 직접 관찰 (Observer 패턴)
- **역할** :
  - **Model**: 데이터와 비즈니스 로직 담당
  - **View**: 사용자 인터페이스 표시
  - **Controller**: Model과 View 사이 중재, 사용자 입력 처리 

```java
// 간단한 MVC 예시
public class UserController {
    private UserModel model;
    private UserView view;
    
    public void createUser(String name, String email) {
        User user = new User(name, email);
        model.addUser(user);
        view.updateUserList(model.getUsers());
    }
}
```

**MVP (Model-View-Presenter)**
- **철학**: View의 순수성 - View는 완전히 수동적
- **특징**: Presenter가 모든 로직 담당, 테스트 용이
- **역할** : 
  - **Model**: MVC와 동일 (데이터와 비즈니스 로직)
  - **View**: 완전히 수동적, Presenter에게 위임
  - **Presenter**: 모든 로직 담당, View와 Model 중재 

```java
// 간단한 MVP 예시
public class UserPresenter {
    private UserView view;
    private UserModel model;
    
    public void onAddUser() {
        String name = view.getName();
        String email = view.getEmail();
        model.addUser(new User(name, email));
        view.showUsers(model.getUsers());
    }
}
```

**MVVM (Model-View-ViewModel)**
- **철학**: 데이터 바인딩 - View와 ViewModel 자동 동기화
- **특징**: 양방향 바인딩, 선언적 UI
- **역할** : 
  - **Model**: MVC와 동일 (데이터와 비즈니스 로직)
  - **View**: UI 표시, ViewModel과 바인딩
  - **ViewModel**: View를 위한 데이터와 로직, Model과 View 중재 

```java
// 간단한 MVVM 예시 (WPF/JavaFX)
public class UserViewModel {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    
    public void addUser() {
        if (isValid()) {
            model.addUser(new User(name.get(), email.get()));
            clearFields();
        }
    }
}
```

### 🎯 현대 트렌드
- **Component-Based**: React, Vue, Angular (2024년 기준)
- **State Management**: Redux, Zustand, Pinia
- **Server-Side Rendering**: Next.js, Nuxt.js, SvelteKit

---

## 2. VO & DTO

### VO (Value Object)
- **특징**: 불변 객체, 값으로 비교
- **목적**: 도메인 개념 명확화

```java
// 간단한 VO 예시
public final class Money {
    private final BigDecimal amount;
    private final String currency;
    
    public Money add(Money other) { 
        return new Money(this.amount.add(other.amount), this.currency);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return amount.equals(other.amount) && currency.equals(other.currency);
    }
}
```

### DTO (Data Transfer Object)
- **특징**: 데이터 전송만 담당, 로직 없음
- **목적**: 계층 간 데이터 이동

```java
// 간단한 DTO 예시
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    
    // getter/setter만 존재
}
```

### 🔄 Mutable vs Immutable
- **Immutable**: 안전함, 함수형 프로그래밍 친화적
- **Mutable**: 성능상 유리하지만 부작용 위험

---

## 3. ORM

### 핵심 개념
- **목적**: 객체 ↔ 관계형 DB 매핑
- **장점**: 생산성 향상, 도메인 중심 설계
- **단점**: N+1 문제, 복잡한 쿼리 제한

```java
// 간단한 JPA 예시
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```

### 🎯 현대 트렌드
- **NoSQL**: MongoDB, Redis, DynamoDB
- **GraphQL**: REST 대안, 클라이언트 중심
- **Edge Computing**: Cloudflare Workers, Vercel Edge

### 🔧 ORM 파생개념
- **Code First**: 코드로부터 데이터베이스 스키마 생성 (JPA, Entity Framework)
- **Database First**: 데이터베이스 스키마로부터 코드 생성 (기존 DB 활용)
- **Migration**: 데이터베이스 스키마 버전 관리 (Flyway, Liquibase)
- **Query DSL**: 타입 안전한 쿼리 작성 (JPA Criteria, QueryDSL)

---

## 4. 팩토리 패턴

### 팩토리 메서드 패턴
- **목적**: 객체 생성 로직 캡슐화
- **특징**: 유효성 검증 포함

```java
// 간단한 팩토리 예시
public class UserFactory {
    public static User createRegularUser(String name, String email) {
        validateInput(name, email);
        return new RegularUser(name, email);
    }
    
    public static User createAdminUser(String name, String email) {
        validateInput(name, email);
        return new AdminUser(name, email);
    }
    
    private static void validateInput(String name, String email) {
        if (name == null || email == null) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
```

### 🎯 현대 트렌드
- **Dependency Injection**: Spring, Dagger, Koin
- **Builder Pattern**: 복잡한 객체 생성
- **Fluent API**: 메서드 체이닝

---

## 📝 요약

1. **MVC/MVP/MVVM**: UI 패턴, 현대는 Component-Based
2. **VO/DTO**: 데이터 표현, Immutable 선호
3. **ORM**: 객체-DB 매핑, NoSQL도 고려
4. **Factory**: 객체 생성, DI로 대체되는 경우 많음

**핵심**: 패턴은 도구일 뿐, 상황에 맞는 선택이 중요!
