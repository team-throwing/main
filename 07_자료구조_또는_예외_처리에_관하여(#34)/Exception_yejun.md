# Java 예외처리 정리

## 1. 예외 계층 구조

### 기본 구조
```
Throwable
├── Error (시스템 레벨 오류) - 복구 불가능
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception
    ├── RuntimeException (Unchecked Exception) - 컴파일 시점 체크 안됨
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   └── IndexOutOfBoundsException
    └── Checked Exception - 컴파일 시점 처리 강제
        ├── IOException
        ├── SQLException
        └── ClassNotFoundException
```

### 언제 사용하는가?
- **Error**: JVM 레벨의 심각한 오류 (복구 불가능)
- **RuntimeException**: 프로그래머의 실수로 발생 (컴파일 시점에 체크 안됨)
- **Checked Exception**: 예상 가능한 오류 (컴파일 시점에 처리 강제)

### 주의사항
- Error는 catch하지 않는 것이 좋음 (복구 불가능)
- RuntimeException은 신중하게 사용 (예상치 못한 버그 원인)

## 2. Checked vs Unchecked Exception

### 언제 사용하는가?
- **Checked Exception**: 호출자가 반드시 처리해야 하는 예외 (파일 I/O, 네트워크 등)
- **Unchecked Exception**: 프로그래머의 실수나 예상치 못한 상황 (null 참조, 잘못된 인수 등)

### 주의사항
- Checked Exception 남발 시 코드 복잡성 증가
- Unchecked Exception은 명시적인 문서화 필요

## 3. try-catch-finally 문법

### 언제 사용하는가?
- **try**: 예외가 발생할 수 있는 코드
- **catch**: 특정 예외 타입 처리
- **finally**: 반드시 실행되어야 하는 정리 작업

### 주의사항
- finally 블록에서도 예외가 발생할 수 있음
- 리소스 정리는 try-with-resources 사용 권장

### ❓ try-with-resources란?
```java
// 기존 방식 (복잡함)
FileInputStream fis = null;
try {
    fis = new FileInputStream("file.txt");
    // 파일 처리
} catch (IOException e) {
    // 예외 처리
} finally {
    if (fis != null) {
        try {
            fis.close();
        } catch (IOException e) {
            // close 예외 처리
        }
    }
}

// try-with-resources 방식 (간단함)
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // 파일 처리
} catch (IOException e) {
    // 예외 처리
} // 자동으로 close() 호출됨
```

## 4. throws, throw 키워드

### 언제 사용하는가?
- **throws**: 예외 처리를 호출자에게 위임하고 싶을 때
- **throw**: 명시적으로 예외를 발생시키고 싶을 때

### 주의사항
- throws는 메서드 시그니처에 명시해야 함
- throw는 적절한 예외 타입 선택 중요

## 5. Custom Exception (커스텀 예외)

### 언제 사용하는가?
- 비즈니스 도메인별 특화된 예외
- API 응답에서 구체적인 오류 정보 제공
- 로깅 및 모니터링에서 구분된 처리

### 주의사항
- 의미 있는 메시지와 컨텍스트 정보 포함
- 적절한 상위 클래스 선택 (Exception vs RuntimeException)

## 6. Exception Chaining (예외 래핑)

### 언제 사용하는가?
- 기술적 예외를 비즈니스 예외로 변환
- 예외 계층 간 정보 전달
- 로깅에서 원본 원인 추적

### 주의사항
- 원본 예외 정보 손실 방지
- 과도한 래핑으로 인한 성능 저하 주의

## 7. 예외 회피 (Propagating/Delegation)

### 언제 사용하는가?
- 예외 처리를 적절한 레벨에서 하기 위해
- 서비스 레이어에서 컨트롤러로 예외 위임
- 기술적 예외를 비즈니스 예외로 변환

### 주의사항
- 적절한 예외 변환과 메시지 제공
- 예외 정보 손실 방지

## 8. 예외 복구 (Recovery/Rescue Pattern)

### 언제 사용하는가?
- 네트워크 오류, 임시적 시스템 오류
- 재시도 가능한 작업
- 대체 방법이 있는 경우

### 주의사항
- 무한 재시도 방지
- 적절한 백오프 전략 적용
- 복구 불가능한 예외 구분

> **핵심**: 재시도 로직은 코드 자체의 문제가 아니라 외부 환경의 임시적 문제(네트워크 지연, 서버 과부하 등)에 대한 복구 전략 / 코드가 정상적으로 작동할 확신이 있을 때 사용

## 9. 리소스 정리 (Try-with-resources, AutoCloseable)

### 언제 사용하는가?
- 파일, 네트워크 연결, 데이터베이스 연결 등
- 메모리 누수 방지
- 확실한 리소스 정리 보장

### 주의사항
- AutoCloseable 인터페이스 올바른 구현
- close() 메서드에서 예외 처리
- 중첩된 리소스 사용 시 주의

> **핵심**: 
> - AutoCloseable은 자주 사용 (Connection, FileInputStream 등)
> - ORM에서는 커넥션 풀로 관리하지만, 직접 Connection을 다룰 때는 필수
> - Python의 `with` 문과 동일한 개념
> - try-with-resources는 () 안의 AutoCloseable 객체가 자동으로 close() 호출

## 10. 예외 로깅 및 모니터링

### 언제 사용하는가?
- 운영 환경에서의 디버깅
- 성능 모니터링
- 사용자 행동 분석

### 주의사항
- 민감한 정보 로깅 금지
- 로그 레벨 적절한 사용
- 구조화된 로깅 형식 사용

> **핵심**: 로깅과 팩토리 메서드 패턴을 결합한 구조화된 예외 처리 방식 / 
각 예외 타입별로 적절한 로깅 레벨과 모니터링 시스템 연동을 제공

---

## 📝 핵심 정리

1. **예외 분류**: Error(복구불가) > RuntimeException(프로그래머실수) > Checked Exception(외부환경)
2. **처리 방식**: try-catch-finally, throws/throw, try-with-resources
3. **고급 패턴**: 커스텀 예외, 예외 래핑, 복구 로직
4. **실무 적용**: 로깅, 모니터링, 리소스 관리