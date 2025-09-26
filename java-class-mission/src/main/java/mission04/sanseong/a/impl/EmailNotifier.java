package mission04.sanseong.a.impl;

import mission04.sanseong.a.Notifier;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[이메일 알림] " + message);
    }
}
