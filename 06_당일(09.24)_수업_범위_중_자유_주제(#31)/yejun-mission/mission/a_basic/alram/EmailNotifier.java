package com.ohgiraffers.mission.a_basic.alram;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("E-mail 알림 : " + message);
    }
}
