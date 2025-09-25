package com.ohgiraffers.mission.a_basic.alram;

public class KakaoNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("까톡카톡 : " + message);
    }
}
