package com.ohgiraffers.mission.a_basic.alram;

public class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("SMS 알림 : " + message);
    }
}
