package com.ohgiraffers.mission.a_basic;

public class Application {

    public static void main(String[] args) {

        Notifier[] notifiers = {
                new EmailNotifier(),
                new SmsNotifier(),
                new KakaoNotifier()
        };

        for (Notifier notifier : notifiers) {
            notifier.send("메시지입니다.");
        }
    }
}
