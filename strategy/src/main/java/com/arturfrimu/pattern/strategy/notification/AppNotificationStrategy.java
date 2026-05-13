package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.AppNotification;
import org.springframework.stereotype.Service;

@Service("APP")
public class AppNotificationStrategy implements NotificationStrategy<AppNotification> {

    @Override
    public void sendNotification(AppNotification notification) {
        System.out.printf(
                "Sending app notification with next parameters.\n\tcontent: %s\n\tsender: %s\n\trecipient: %s\n\tappSpecificContent: %s\n%n",
                notification.content,
                notification.sender,
                notification.recipient,
                notification.getAppSpecificContent()
        );
    }
}
