package com.ohgiraffers.z_activity.mission.c_deep;

public class NaverPay implements PaymentMethod {

    @Override
    public void pay(int amount) {
        System.out.print("네이버페이로 " + amount + "원 결제 완료.");
    }
}
