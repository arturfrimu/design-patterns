package com.arturfrimu.pattern.command.diner;

public class Customer {
    private final String name;
    Waitress waitress;
    Order order;

    public Customer(String name, Waitress waitress) {
        this.name = name;
        System.out.println("Creating Customer(" + name + ")...");
        System.out.println("Inject waitress " + waitress + " in customer " + name + "...");
        this.waitress = waitress;
    }

    public void createOrder(Order order) {
        System.out.println("Customer.createOrder(" + order + ")...");
        this.order = order;
    }

    public void hungry() {
        System.out.println("Customer.hungry()...");
        waitress.takeOrder(order);
    }

    @Override
    public String toString() {
        return name;
    }
}