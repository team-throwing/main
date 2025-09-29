package com.ohgiraffers.mission.a_basic;

public class KakaoNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println("[카카오톡 알림]: " + message);
    }
}
