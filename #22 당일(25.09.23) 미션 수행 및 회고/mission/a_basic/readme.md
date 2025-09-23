
### **[기초] Beginner: 은행 계좌 관리 (캡슐화)**

-   **목표**: 객체의 상태(데이터)와 행동(메서드)을 정의하는 클래스를 설계하고, `private` 접근 제어자와 `getter/setter`를 통해 캡슐화를 적용한다.
-   **요구사항**:
    1.  `BankAccount` 클래스를 설계합니다.
    2.  멤버 변수: `accountNumber`(계좌번호, String), `balance`(잔액, long)를 `private`으로 선언합니다.
    3.  생성자를 통해 계좌번호와 초기 잔액을 설정합니다.
    4.  메서드: `deposit(long amount)`(입금), `withdraw(long amount)`(출금), `getBalance()`(잔액 조회)를 `public`으로 구현합니다.
    5.  `withdraw` 메서드에는 잔액이 출금 요청 금액보다 적을 경우, "잔액이 부족합니다." 메시지를 출력하고 출금을 막는 유효성 검사 로직을 포함해야 합니다.
