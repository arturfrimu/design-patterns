package com.arturfrimu.pattern.command.diner.order;

import com.arturfrimu.pattern.command.diner.Cook;
import com.arturfrimu.pattern.command.diner.Order;

public class BurgerAndFriesOrder implements Order {
    private final String name;
    Cook cook;

    public BurgerAndFriesOrder(String name, Cook cook) {
        this.name = name;
        System.out.println("Creating order " + name + "...");
        System.out.println("Inject cook " + cook + " in order " + name + "...");
        this.cook = cook;
    }

    public void orderUp() {
        System.out.println("Start : " + name + ".orderUp()...");
        cook.makeBurger();
        cook.makeFries();
        System.out.println("End : " + name + ".orderUp()...");
    }

    @Override
    public String toString() {
        return name;
    }
}
