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
            logger.log(message);
        }
    }
}
