package com.ohgiraffers.mission.a_basic;

public class KakaoNotifier extends Notifier {
    @Override
    public void send(String message) {
        System.out.println("[Kakao 알림]: " +  message);
    }
}
