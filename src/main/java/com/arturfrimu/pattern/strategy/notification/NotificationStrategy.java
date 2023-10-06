package com.arturfrimu.pattern.strategy.notification;

import static com.arturfrimu.pattern.strategy.notification.NotificationStrategy.*;

public interface NotificationStrategy<T extends Notification> {

    void sendNotification(T notification);

    abstract class Notification {
        protected Long id;
        protected String content;
        protected String sender;
        protected String recipient;

        protected Notification(String content, String sender, String recipient) {
            this.content = content;
            this.sender = sender;
            this.recipient = recipient;
        }
    }

    class EmailNotification extends Notification {
        protected EmailNotification(String content, String sender, String recipient) {
            super(content, sender, recipient);
        }
    }
    class SmsNotification extends Notification {
        protected SmsNotification(String content, String sender, String recipient) {
            super(content, sender, recipient);
        }
    }
}
