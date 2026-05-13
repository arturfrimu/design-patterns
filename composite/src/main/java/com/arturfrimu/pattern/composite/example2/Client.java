package com.arturfrimu.pattern.composite.example2;

public class Client {
    public static void main(String[] args) {
        Notification emailNotification = new EmailNotification(true);
        Notification smsNotification = new SmsNotification(true);

        CompositeNotification compositeNotification = new CompositeNotification();

        compositeNotification.addNotification(emailNotification);
        compositeNotification.addNotification(smsNotification);

        compositeNotification.send("Good notice !!");

        smsNotification.disable();

        compositeNotification.send("Good notice again !!");
    }
}
