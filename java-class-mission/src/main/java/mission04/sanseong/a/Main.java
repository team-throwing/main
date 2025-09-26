package mission04.sanseong.a;

import mission04.sanseong.a.impl.EmailNotifier;
import mission04.sanseong.a.impl.KakaoNotifier;
import mission04.sanseong.a.impl.SmsNotifier;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Notifier smsNotifier = new SmsNotifier();
        Notifier emailNotifier = new EmailNotifier();
        Notifier kakaoNotifier = new KakaoNotifier();

        List<Notifier> notifiers = List.of(smsNotifier, emailNotifier, kakaoNotifier);

        notifiers.forEach(it -> it.send("보내진 메세지"));
    }
}
