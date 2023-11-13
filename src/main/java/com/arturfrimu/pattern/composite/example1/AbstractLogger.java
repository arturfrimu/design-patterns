package com.arturfrimu.pattern.composite.example1;

public abstract class AbstractLogger implements Logger {

    protected boolean isEnabled;

    protected AbstractLogger() {
        this.isEnabled = false;
    }

    protected AbstractLogger(boolean isEnabled) {
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
