package com.arturfrimu.pattern.command.dinner.java.order;

import com.arturfrimu.pattern.command.dinner.java.Cook;
import com.arturfrimu.pattern.command.dinner.java.Order;

public class SteakOrder implements Order {
    private final String name;
    Cook cook;

    public SteakOrder(String name, Cook cook) {
        this.name = name;
        System.out.println("Creating order " + name + "...");
        System.out.println("Inject cook " + cook + " in order " + name + "...");
        this.cook = cook;
    }

    public void orderUp() {
        System.out.println("Start : " + name + ".orderUp()...");
        cook.makeSteak();
        System.out.println("End : " + name + ".orderUp()...");
    }

    @Override
    public String toString() {
        return name;
    }
}
