package com.arturfrimu.pattern.composite.example1;

import java.util.ArrayList;
import java.util.List;

public class CompositeLogger implements Logger {

    private final List<Logger> loggers = new ArrayList<>();

    public void addLogger(Logger logger) {
        this.loggers.add(logger);
    }

    public void removeLogger(Logger logger) {
        this.loggers.remove(logger);
    }

    @Override
    public void log(String message) {
        for (Logger logger : this.loggers) {
            if (logger.isEnabled()) {
                logger.log(message);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void enable() {
        throw new UnsupportedOperationException("Cannot enable/disable CompositeLogger directly.");
    }

    @Override
    public void disable() {
        throw new UnsupportedOperationException("Cannot enable/disable CompositeLogger directly.");
    }
}
