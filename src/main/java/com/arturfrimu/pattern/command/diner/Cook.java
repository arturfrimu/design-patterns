package com.arturfrimu.pattern.command.diner;

public class Cook {
    private final String name;

    public Cook(String name) {
        System.out.println("Creating Cook " + name + "...");
        this.name = name;
    }

    public void makeBurger() {
        System.out.println("Cook.makeBurger()... Making a burger...");
    }

    public void makeFries() {
        System.out.println("Cook.makeFries()... Making fries...");
    }

    public void makeSteak() {
        System.out.println("Cook.makeSteak()... Making steak...");
    }

    @Override
    public String toString() {
        return name;
    }
}