package com.arturfrimu.pattern.composite.example2;

public abstract class AbstractNotification implements Notification {

    protected boolean isEnabled;

    protected AbstractNotification() {
        this.isEnabled = false;
    }

    protected AbstractNotification(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public void enable() {
        this.isEnabled = true;
    }

    @Override
    public void disable() {
        this.isEnabled = false;
    }
}
