# 주니어 개발자를 위한 소프트웨어 아키텍처 가이드

## 아키텍처란?

소프트웨어를 어떻게 **구조화하고 조직화할지**를 정하는 큰 그림

### 기본 분류
- **패턴**: 구체적인 구조 (레이어드, MVC, 마이크로서비스)
- **스타일**: 설계 철학 (클린, 헥사고날, 어니언)
- **배포**: 모놀리식 vs 마이크로서비스

> 패턴과 스타일은 종종 혼용되지만, 여기서는 '패턴'을 구체적 구조로, '스타일'을 설계 철학으로 구분합니다.

---

## 1. 기본 원칙: SRP (단일 책임 원칙)

**정의**: 하나의 클래스는 하나의 이유로만 변경되어야 한다.

### 간단 예시
```java
// 나쁜 예: 하나의 클래스가 여러 책임을 가짐
public class UserManager {
    public void createUser(String email, String password) {
        // 1. 비밀번호 검증 (보안 책임)
        if (password.length() < 8) {
            throw new Exception("비밀번호는 8자 이상이어야 합니다");
        }
        
        // 2. 이메일 검증 (유효성 책임)
        if (!email.contains("@")) {
            throw new Exception("올바른 이메일이 아닙니다");
        }
        
        // 3. 데이터베이스 저장 (저장 책임)
        database.save(new User(email, password));
        
        // 4. 환영 이메일 발송 (알림 책임)
        emailService.send("환영합니다!", email);
    }
}

// 좋은 예: 각 클래스가 하나의 책임만 가짐
public class UserService {
    private PasswordValidator passwordValidator;  // 비밀번호 검증만
    private EmailValidator emailValidator;        // 이메일 검증만
    private UserRepository userRepository;        // 저장만
    private EmailService emailService;            // 이메일 발송만
    
    public void createUser(String email, String password) {
        passwordValidator.validate(password);
        emailValidator.validate(email);
        
        User user = new User(email, password);
        userRepository.save(user);
        emailService.sendWelcomeEmail(email);
    }
}
```

**왜 좋은가?**
- 비밀번호 규칙이 바뀌면 → `PasswordValidator`만 수정
- 이메일 형식이 바뀌면 → `EmailValidator`만 수정
- 저장 방식이 바뀌면 → `UserRepository`만 수정
- 각각 독립적으로 테스트 가능

> **참고 키워드**
> - **핵심**: `SOLID 원칙`, `단일 책임 원칙`, `관심사 분리`
> - **연관**: `응집도`, `결합도`, `리팩토링`
> - **확장**: `도메인 주도 설계`, `마이크로서비스`, `컴포넌트 설계`

---

## 2. 핵심 철학: 의존성 역전 원칙

아키텍처 설계에서 가장 중요한 원칙 중 하나입니다.

### 기존 시스템의 문제점
```java
// 기존 방식: 도메인이 외부 기술에 의존
public class OrderService {
    private MySQLDatabase database;  // MySQL에 강하게 결합
    
    public void saveOrder(Order order) {
        // MySQL 전용 코드가 도메인에 섞임
        String sql = "INSERT INTO orders VALUES (...)";
        database.execute(sql);
    }
}
```

**문제점**: 
- MySQL → PostgreSQL로 변경하려면 도메인 코드 수정 필요
- 테스트할 때 실제 DB가 필요
- 도메인 로직이 DB 기술에 종속됨

### 의존성 역전 적용 후
```java
// 개선된 방식: 도메인이 인터페이스에 의존
public class OrderService {
    private OrderRepository repository;  // 인터페이스에 의존
    
    public void saveOrder(Order order) {
        repository.save(order);  // 도메인 로직만 남음
    }
}

// 외부에서 구현체 주입
interface OrderRepository {
    void save(Order order);
}

class MySQLOrderRepository implements OrderRepository { ... }
class PostgreSQLOrderRepository implements OrderRepository { ... }
```

**개선점**:
- 도메인은 인터페이스만 알고, 구현체는 외부에서 주입
- DB 교체 시 구현체만 바꾸면 됨
- 테스트 시 Mock 객체 사용 가능

### 의존성 역전 원칙 vs 의존성 주입

**의존성 역전 원칙 (Dependency Inversion Principle)**
- SOLID 원칙 중 하나로, **설계 원칙/철학**
- "고수준 모듈은 저수준 모듈에 의존하면 안 되고, 둘 다 추상화에 의존해야 한다"
- **의존 방향을 역전**시키는 것이 핵심

### 의존성 역전 원칙을 적용하는 방법들

**1. 의존성 주입 (DI)**
```java
// 생성자 주입
public OrderService(OrderRepository repository) {
    this.repository = repository;
}

// Spring의 @Autowired
@Autowired
private OrderRepository repository;
```

**2. 팩토리 패턴**
```java
public class OrderService {
    private OrderRepository repository;
    
    public OrderService() {
        this.repository = RepositoryFactory.create();  // 팩토리에서 생성
    }
}
```

**3. 서비스 로케이터 패턴**
```java
public class OrderService {
    private OrderRepository repository;
    
    public OrderService() {
        this.repository = ServiceLocator.get(OrderRepository.class);  // 서비스 로케이터에서 가져옴
    }
}
```

> **참고 키워드**
> - **핵심**: `의존성 역전 원칙`, `Dependency Inversion Principle`, `인터페이스 분리`
> - **연관**: `의존성 주입`, `DI Container`, `Spring Framework`
> - **확장**: `팩토리 패턴`, `서비스 로케이터`, `인버전 오브 컨트롤`

---

## 3. 주요 아키텍처 스타일

### 3.1 레이어드 아키텍처
**철학**: 위에서 아래로 관심사 분리
**구조**: Controller → Service → Repository
**장점**: 단순하고 배우기 쉬움
**단점**: 서비스가 비대해지기 쉬움

> **참고 키워드**
> - **핵심**: `레이어드 아키텍처`, `3-tier 아키텍처`, `관심사 분리`
> - **연관**: `MVC 패턴`, `Spring Boot 구조`, `웹 애플리케이션 아키텍처`
> - **확장**: `레이어드 모놀리스`, `마이크로서비스 전환`, `도메인 모델`

### 3.2 헥사고날 아키텍처
**철학**: 도메인 중심, 포트로 외부와 연결
**핵심**: 도메인이 외부 기술에 의존하지 않음
**장점**: DB/API 쉽게 교체 가능
**단점**: 초기 학습 비용

> **참고 키워드**
> - **핵심**: `헥사고날 아키텍처`, `Ports and Adapters`, `도메인 중심 설계`
> - **연관**: `의존성 역전`, `인터페이스 분리`, `어댑터 패턴`
> - **확장**: `테스트 주도 개발`, `도메인 서비스`, `애그리게이트`

### 3.3 클린 아키텍처
**철학**: 도메인과 유즈케이스가 핵심
**구조**: 엔티티 → 유즈케이스 → 어댑터 → 프레임워크
**장점**: 프레임워크 독립성
**단점**: 작은 프로젝트에는 과할 수 있음

> **참고 키워드**
> - **핵심**: `Clean Architecture`, `Uncle Bob`, `유즈케이스`
> - **연관**: `의존성 규칙`, `엔티티`, `인터페이스 어댑터`
> - **확장**: `헥사고날 아키텍처`, `어니언 아키텍처`, `도메인 주도 설계`

---

## 4. 주니어 개발자 선택 가이드

| 상황 | 추천 아키텍처 | 이유 |
|------|---------------|------|
| 작은 프로젝트, 빠른 학습 | 레이어드 | 단순하고 직관적 |
| 복잡한 도메인, 장기 유지보수 | 헥사고날/클린 | 도메인 보호, 확장성 |
| 팀 전체가 주니어 | 레이어드 → 점진적 발전 | 단계적 학습 |

**학습 순서 추천**: 레이어드 → MVC → 헥사고날 → 클린

> **참고 키워드**
> - **핵심**: `아키텍처 선택`, `점진적 개선`, `리팩토링`
> - **연관**: `레거시 코드`, `코드 리뷰`, `팀 개발`
> - **확장**: `아키텍처 마이그레이션`, `도메인 모델링`, `이벤트 스토밍`

---

## 5. 마무리

아키텍처는 **도메인을 보호하고 외부 의존성을 최소화하는 방향**으로 발전해 나가는 것이 핵심  

> **추가 추천 키워드**
> - **성능**: `아키텍처 성능`, `확장성 설계`, `캐싱 전략`
> - **보안**: `보안 아키텍처`, `인증/인가`, `데이터 보호`  
> - **모니터링**: `로깅 전략`, `메트릭 수집`, `분산 추적`
> - **최신 트렌드**: `서버리스 아키텍처`, `이벤트 기반 아키텍처`, `GraphQL`