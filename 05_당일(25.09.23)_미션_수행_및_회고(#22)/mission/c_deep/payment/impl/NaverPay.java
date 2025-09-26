package com.ohgiraffers.z_activity.mission.c_deep.payment.impl;

import com.ohgiraffers.z_activity.mission.c_deep.payment.PaymentMethod;

public class NaverPay implements PaymentMethod {
    @Override
    public Integer pay(Integer amount) {
        System.out.println("[카카오페이]" + amount + "만큼 결제");
        return amount;
    }
}
