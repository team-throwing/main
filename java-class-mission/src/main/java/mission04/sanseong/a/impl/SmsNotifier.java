package mission04.sanseong.a.impl;

import mission04.sanseong.a.Notifier;

public class SmsNotifier implements Notifier {
    public void send(String message) {
        System.out.println("[Sms 알림] " + message);
    }
}
