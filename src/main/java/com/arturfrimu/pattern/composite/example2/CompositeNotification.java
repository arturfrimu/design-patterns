package com.arturfrimu.pattern.composite.example2;

import java.util.ArrayList;
import java.util.List;

public class CompositeNotification extends AbstractNotification {

    private final List<Notification> notifications = new ArrayList<>();

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    public void remove(Notification notification) {
        this.notifications.remove(notification);
    }

    @Override
    public void send(String message) {
        for (Notification notification : notifications) {
            if (notification.isEnabled()) {
                notification.send(message);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void enable() {
        throw new UnsupportedOperationException("Cannot enable/disable CompositeNotification directly.");
    }

    @Override
    public void disable() {
        throw new UnsupportedOperationException("Cannot enable/disable CompositeNotification directly.");
    }
}
