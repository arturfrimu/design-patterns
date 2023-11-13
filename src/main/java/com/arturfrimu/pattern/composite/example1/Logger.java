package com.arturfrimu.pattern.composite.example1;

public interface Logger {
    void log(String message);

    boolean isEnabled();

    void enable();

    void disable();
}
