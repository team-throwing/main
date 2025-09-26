package com.ohgiraffers.oop.z_activity.mission.c_deep;

public class NaverPay implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("네이버페이로 " + amount + "원 결제 완료");
    }
}
