package com.ohgiraffers.mission.a_basic;

import com.ohgiraffers.mission.a_basic.alram.EmailNotifier;
import com.ohgiraffers.mission.a_basic.alram.KakaoNotifier;
import com.ohgiraffers.mission.a_basic.alram.Notifier;
import com.ohgiraffers.mission.a_basic.alram.SmsNotifier;

public class Solution {
    public static void main(String[] args) {

        Notifier[] notifiers = {new EmailNotifier(),new SmsNotifier(), new KakaoNotifier()};

        for (Notifier notifier : notifiers) {
            notifier.send("비상!");
        }
    }

}

