package com.arturfrimu.pattern.composite.example1;

import java.io.File;

public class Client {

    public static void main(String[] args) {
        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger(new File("logs"));

        CompositeLogger compositeLogger = new CompositeLogger();
        compositeLogger.addLogger(consoleLogger);
        compositeLogger.addLogger(fileLogger);

        compositeLogger.log("Hello my friends !");
    }
}
