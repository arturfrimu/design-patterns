package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationController.NotificationRequest;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.EmailNotification;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.Notification;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification(NotificationRequest request) {
        return new EmailNotification(request.getContent(), request.getSender(), request.getRecipient());
    }

    @Override
    public boolean supports(String strategyType) {
        return "EMAIL".equalsIgnoreCase(strategyType);
    }
}
