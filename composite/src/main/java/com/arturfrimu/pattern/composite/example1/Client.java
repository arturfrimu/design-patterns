package com.arturfrimu.pattern.composite.example1;

import java.io.File;

public class Client {

    public static void main(String[] args) {
        Logger consoleLogger = new ConsoleLogger(true);
        Logger fileLogger = new FileLogger(new File("logs"), true);

        CompositeLogger compositeLogger = new CompositeLogger();
        compositeLogger.addLogger(consoleLogger);
        compositeLogger.addLogger(fileLogger);

        compositeLogger.log("Hello my friends !");

        fileLogger.disable();

        compositeLogger.log("Hello my friends again !");
    }
}
