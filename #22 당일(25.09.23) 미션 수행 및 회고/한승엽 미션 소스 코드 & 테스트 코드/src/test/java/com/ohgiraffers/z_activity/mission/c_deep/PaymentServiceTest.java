package com.ohgiraffers.z_activity.mission.c_deep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    PaymentService paymentService = new PaymentService();

    /// 콘솔 출력 테스트 ///
    private static ByteArrayOutputStream outputStream;

    @BeforeEach
    void setOutStream() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStream() {
        System.setOut(System.out);
    }

    @Test
    void processPayment_naverPay_success() {
        // given
        int amount = 1000;
        PaymentMethod paymentMethod = new NaverPay();

        // when
        paymentService.processPayment(paymentMethod, amount);

        // then
        Assertions.assertEquals(outputStream.toString(), "네이버페이로 " + amount + "원 결제 완료.");
    }

    @Test
    void processPayment_kakaoPay_success() {
        // given
        int amount = 1000;
        PaymentMethod paymentMethod = new KakaoPay();

        // when
        paymentService.processPayment(paymentMethod, amount);

        // then
        Assertions.assertEquals(outputStream.toString(), "카카오페이로 " + amount + "원 결제 완료.");
    }

    @Test
    void processPayment_creditCard_success() {
        // given
        int amount = 1000;
        PaymentMethod paymentMethod = new CreditCard();

        // when
        paymentService.processPayment(paymentMethod, amount);

        // then
        Assertions.assertEquals(outputStream.toString(), "신용카드로 " + amount + "원 결제 완료.");
    }
}