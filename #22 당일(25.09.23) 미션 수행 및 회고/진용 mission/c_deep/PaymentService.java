package com.ohgiraffers.oop.z_activity.mission.c_deep;

public class PaymentService {

    public static void processPayment(PaymentMethod paymentMethod, int amount) {
        paymentMethod.pay(amount);
    }
}
