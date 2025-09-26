package com.ohgiraffers.oop.z_activity.mission.c_deep;

public class Main {

    public static void main(String[] args) {
        CreditCard creditCard = new CreditCard();
        KakaoPay kPay = new KakaoPay();
        NaverPay nPay = new NaverPay();

        PaymentService.processPayment(creditCard, 1000);
        PaymentService.processPayment(kPay, 1200);
        PaymentService.processPayment(nPay, 1100);
    }
}
