package com.arturfrimu.pattern.composite.example1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {

    private final File file;

    public FileLogger(File file) {
        this.file = file;
    }

    @Override
    public void log(String message) {
        boolean append = true;
        try (FileWriter writer = new FileWriter(this.file, append)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
