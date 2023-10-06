package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationController.NotificationRequest;
import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.Notification;

public interface NotificationFactory {

    Notification createNotification(NotificationRequest request);

    boolean supports(String strategyType);
}

