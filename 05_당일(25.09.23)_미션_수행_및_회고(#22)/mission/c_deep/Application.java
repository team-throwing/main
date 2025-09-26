package com.ohgiraffers.z_activity.mission.c_deep;

import com.ohgiraffers.z_activity.mission.c_deep.payment.PaymentMethod;
import com.ohgiraffers.z_activity.mission.c_deep.payment.impl.CreditCard;
import com.ohgiraffers.z_activity.mission.c_deep.payment.impl.KakaoPay;
import com.ohgiraffers.z_activity.mission.c_deep.payment.impl.NaverPay;
import com.ohgiraffers.z_activity.mission.c_deep.service.PaymentService;

public class Application {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        PaymentMethod creditCard = new CreditCard();
        PaymentMethod kakaoPay = new KakaoPay();
        PaymentMethod naverPay = new NaverPay();

        paymentService.paymentProcess(creditCard, 5);
        paymentService.paymentProcess(kakaoPay, 10);
        paymentService.paymentProcess(naverPay, 15);
    }
}
