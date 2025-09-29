package com.ohgiraffers.mission.a_basic;

public class EmailNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println("[이메일 알림]: " + message);
    }
}
