package com.ohgiraffers.mission.a_basic;

public class EmailNotifier extends Notifier {
    @Override
    public void send(String message) {
        System.out.println("[Email 알림]: " +  message);
    }
}
