package com.ohgiraffers.oop.z_activity.mission.c_deep;

public class KakaoPay implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("카카오페이로 " + amount + "원 결제 완료");
    }
}
