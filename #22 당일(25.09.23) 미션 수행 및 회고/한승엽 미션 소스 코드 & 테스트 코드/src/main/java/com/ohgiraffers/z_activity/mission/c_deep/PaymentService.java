package com.ohgiraffers.z_activity.mission.c_deep;

public class PaymentService {

    public void processPayment(PaymentMethod paymentMethod, int amount) {
        paymentMethod.pay(amount);
    }
}
