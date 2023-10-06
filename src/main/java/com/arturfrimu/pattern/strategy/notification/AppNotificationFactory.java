package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationController.NotificationRequest;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.AppNotification;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.Notification;
import org.springframework.stereotype.Component;

@Component
public class AppNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification(NotificationRequest request) {
        return new AppNotification(request.getContent(), request.getSender(), request.getRecipient(), request.getAppSpecificContent());
    }

    @Override
    public boolean supports(String strategyType) {
        return "APP".equalsIgnoreCase(strategyType);
    }
}
