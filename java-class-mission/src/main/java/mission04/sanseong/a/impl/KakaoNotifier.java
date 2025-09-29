package mission04.sanseong.a.impl;

import mission04.sanseong.a.Notifier;

public class KakaoNotifier implements Notifier {
    public void send(String message) {
        System.out.println("[Kakao 알림] " + message);
    }
}
