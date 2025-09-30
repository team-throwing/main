package com.ohgiraffers.mission.a_basic;

public class SmsNotifier extends Notifier {
    @Override
    public void send(String message) {
        System.out.println("[SMS 알림]: " +  message);
    }
}
