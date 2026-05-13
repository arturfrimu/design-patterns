package com.arturfrimu.pattern.command.dinner.java;

public class Waitress {
    private final String name;
    Order order;

    public Waitress(String name) {
        System.out.println("Creating Waitress " + name + "...");
        this.name = name;
    }

    public void takeOrder(Order order) {
        System.out.println("Waitress.takeOrder(" + order + ")...");
        this.order = order;
        order.orderUp();
    }

    @Override
    public String toString() {
        return name;
    }
}