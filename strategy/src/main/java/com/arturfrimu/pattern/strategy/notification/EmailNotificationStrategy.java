package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.EmailNotification;
import org.springframework.stereotype.Service;

@Service("EMAIL")
public class EmailNotificationStrategy implements NotificationStrategy<EmailNotification> {

    @Override
    public void sendNotification(EmailNotification notification) {
        System.out.printf(
                "Sending email with next parameters.\n\tcontent: %s\n\tsender: %s\n\trecipient: %s\n\temailSpecificContent: %s\n%n",
                notification.content,
                notification.sender,
                notification.recipient,
                notification.getEmailSpecificContent()
        );
    }
}
