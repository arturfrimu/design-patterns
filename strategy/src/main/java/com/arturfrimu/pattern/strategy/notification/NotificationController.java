package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationStrategy.Notification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private Map<String, NotificationStrategy> strategies;
    @Autowired
    private List<NotificationFactory> notificationFactories;

    @PostMapping
    public ResponseEntity<String> sendNotification(
            @RequestParam(value = "strategyType", defaultValue = "EMAIL") String strategyType,
            @RequestBody NotificationRequest notificationRequest
    ) {
        NotificationFactory factory = notificationFactories.stream()
                .filter(f -> f.supports(strategyType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid strategy type"));

        Notification notification = factory.createNotification(notificationRequest);

        strategies.get(strategyType.toUpperCase()).sendNotification(notification);

        return ResponseEntity.ok("OK");
    }

    @Getter
    @Setter
    static class NotificationRequest {
        private String content;
        private String sender;
        private String recipient;
        private String emailSpecificContent;
        private String smsSpecificContent;
        private String appSpecificContent;
    }
}
