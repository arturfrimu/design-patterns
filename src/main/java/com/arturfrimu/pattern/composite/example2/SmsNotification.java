package com.arturfrimu.pattern.composite.example2;

public class SmsNotification extends AbstractNotification {

    public SmsNotification(boolean isEnabled) {
        super(isEnabled);
    }

    @Override
    public void send(String message) {
        System.out.printf("Sms notification: %s%n", message);
    }
}
