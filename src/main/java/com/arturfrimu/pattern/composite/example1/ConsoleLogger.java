package com.arturfrimu.pattern.composite.example1;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(boolean isEnabled) {
        super(isEnabled);
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
