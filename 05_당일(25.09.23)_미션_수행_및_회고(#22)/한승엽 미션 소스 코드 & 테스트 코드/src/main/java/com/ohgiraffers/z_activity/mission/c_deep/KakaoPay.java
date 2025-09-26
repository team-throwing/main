package com.ohgiraffers.z_activity.mission.c_deep;

public class KakaoPay implements PaymentMethod {

    @Override
    public void pay(int amount) {
        System.out.print("카카오페이로 " + amount + "원 결제 완료.");
    }
}
