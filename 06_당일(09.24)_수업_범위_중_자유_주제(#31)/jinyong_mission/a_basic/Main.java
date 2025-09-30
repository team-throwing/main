package com.ohgiraffers.mission.a_basic;

public class Main {

    public static void main(String[] args) {
        Notifier[] notifiers = {new EmailNotifier(), new KakaoNotifier(), new SmsNotifier()};


        for (Notifier notifier : notifiers) {
            notifier.send("hello");
        }


    }
}
