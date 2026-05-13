package com.arturfrimu.pattern.composite.example2;

public class EmailNotification extends AbstractNotification {

    public EmailNotification(boolean isEnabled) {
        super(isEnabled);
    }

    @Override
    public void send(String message) {
        System.out.printf("Email notification: %s%n", message);
    }
}
