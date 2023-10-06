package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationController.NotificationRequest;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.Notification;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification(NotificationRequest request) {
        return new SmsNotification(request.getContent(), request.getSender(), request.getRecipient());
    }

    @Override
    public boolean supports(String strategyType) {
        return "SMS".equalsIgnoreCase(strategyType);
    }
}
