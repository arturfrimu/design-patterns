package com.arturfrimu.pattern.command.party.java;

public class Light {
    private final String location;
    private int level;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        level = 100;
        System.out.println("Light is on in " + location);
    }

    public void off() {
        level = 0;
        System.out.println("Light is off in " + location);
    }

    public void dim(int level) {
        this.level = level;
        if (level == 0) {
            off();
        } else {
            System.out.println("Light is dimmed to " + level + "%");
        }
    }

    public int getLevel() {
        return level;
    }
}
