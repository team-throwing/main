package com.ohgiraffers.z_activity.mission.c_deep;

public class CreditCard implements PaymentMethod {

    @Override
    public void pay(int amount) {
        System.out.print("신용카드로 " + amount + "원 결제 완료.");
    }
}
