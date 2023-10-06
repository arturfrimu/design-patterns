package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.SmsNotification;
import org.springframework.stereotype.Service;

@Service("SMS")
public class SmsNotificationStrategy implements NotificationStrategy<SmsNotification> {

    @Override
    public void sendNotification(SmsNotification notification) {
        System.out.println(String.format(
                "Sending email with next parameters.\n\tcontent: %s\n\tsender: %s\n\trecipient: %s\n",
                notification.content,
                notification.sender,
                notification.recipient
        ));
    }
}
