package com.arturfrimu.pattern.strategy.notification;

import lombok.Getter;

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

    @Getter
    class EmailNotification extends Notification {
        private String emailSpecificContent;

        protected EmailNotification(String content, String sender, String recipient, String emailSpecificContent) {
            super(content, sender, recipient);
            this.emailSpecificContent = emailSpecificContent;
        }
    }

    @Getter
    class SmsNotification extends Notification {
        private String smsSpecificContent;

        protected SmsNotification(String content, String sender, String recipient, String smsSpecificContent) {
            super(content, sender, recipient);
            this.smsSpecificContent = smsSpecificContent;
        }
    }

    @Getter
    class AppNotification extends Notification {
        private String appSpecificContent;

        protected AppNotification(String content, String sender, String recipient, String appSpecificContent) {
            super(content, sender, recipient);
            this.appSpecificContent = appSpecificContent;
        }
    }
}
