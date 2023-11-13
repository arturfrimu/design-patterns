package com.arturfrimu.pattern.composite.example1;

public class ConsoleLogger implements Logger {

    private boolean isEnabled;

    public ConsoleLogger() {
        this.isEnabled = false;
    }

    public ConsoleLogger(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
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
