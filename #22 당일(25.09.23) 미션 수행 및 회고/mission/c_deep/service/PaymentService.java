package com.ohgiraffers.z_activity.mission.c_deep.service;

import com.ohgiraffers.z_activity.mission.c_deep.payment.PaymentMethod;

public class PaymentService {
    public void paymentProcess(PaymentMethod paymentMethod, Integer amount){
        paymentMethod.pay(amount);
    }
}
