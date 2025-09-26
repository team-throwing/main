### **[심화] Advanced: 결제 시스템 설계 (추상화와 다형성)**

-   **목표**: 추상 클래스와 인터페이스를 사용하여 역할을 정의하고, 다형성을 활용해 결합도는 낮추고 유연성은 높은 시스템을 설계합니다.
-   **요구사항**:
    1.  `PaymentMethod`라는 `pay(int amount)` 추상 메서드를 가진 **인터페이스**를 정의합니다.
    2.  `PaymentMethod`를 구현하는 `CreditCard`, `KakaoPay`, `NaverPay` 클래스를 작성합니다. 각 클래스는 `pay` 메서드를 통해 결제 수단에 맞는 메시지(예: "신용카드로 10000원 결제 완료.")를 출력합니다.
    3.  결제를 처리하는 `PaymentService` 클래스를 작성합니다.
    4.  `PaymentService`는 `processPayment(PaymentMethod paymentMethod, int amount)` 메서드를 가집니다. 이 메서드는 매개변수로 전달받은 **어떤 결제 수단이든** `pay` 메서드를 호출하여 결제를 수행합니다.
    5.  `main` 메서드에서 `CreditCard`, `KakaoPay` 객체를 생성하여 동일한 `processPayment` 메서드를 호출해도 각기 다른 결제 방식이 다형적으로 동작함을 보입니다.