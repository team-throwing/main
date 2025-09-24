package com.ohgiraffers.activity.mission.c_deep;

public class Sol {
    public static void main(String[] args) {
        // 결제 수단 생성
        CreditCard creditCard = new CreditCard();
        KakaoPay kakaoPay = new KakaoPay();
        NaverPay naverPay = new NaverPay();

        // 결제 서비스처리
        PaymentService paymentService = new PaymentService();
        paymentService.processPayment(creditCard, 1000);
        paymentService.processPayment(kakaoPay, 2000);
        paymentService.processPayment(naverPay, 3000);

    }
}

// 결제수단 추상클래스 (method만 있어서 Interface로 하는게 더 좋긴함)
abstract class PaymentMethod {
    abstract void pay(int amount);
}

class CreditCard extends PaymentMethod {
    @Override
    void pay(int amount) {
        System.out.println("신용카드로 "+ amount + "원 결제 완료.");
    }
}

class KakaoPay extends PaymentMethod {
    @Override
    void pay(int amount) {
        System.out.println("카카오페이로 "+amount+ "원 결제 완료.");
    }
}

class NaverPay extends PaymentMethod {
    @Override
    void pay(int amount) {
        System.out.println("네이버페이로 "+amount + "원 결제 완료.");
    }
}

// 결제 서비스 
class PaymentService {

    // 서비스 생성시, 결제 수단과 결제금액 받고 처리
    public void processPayment(PaymentMethod paymentMethod, int amount) {
        // 굳이 명시적 형변환 필요없는데 사용했음.... 2분 같은의견
        paymentMethod.pay(amount)
        
        // 17 에서는 preview라 안되고, 21부터 가능
//        switch (paymentMethod.getClass().getSimpleName()) {
//            case "CreditCard" -> ((CreditCard) paymentMethod).pay(amount);
//            case "NaverPay" -> ((NaverPay) paymentMethod).pay(amount);
//            case "KakaoPay" -> ((KakaoPay) paymentMethod).pay(amount);
//            default -> {
//                // 없는디?
//            }
//
//        }
    }
}
