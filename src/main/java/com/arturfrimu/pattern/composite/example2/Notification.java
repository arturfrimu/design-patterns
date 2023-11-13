package com.arturfrimu.pattern.composite.example2;

public interface Notification {
    void send(String message);

    boolean isEnabled();

    void enable();

    void disable();
}
