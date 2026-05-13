package com.arturfrimu.pattern.command.dinner.java;

import com.arturfrimu.pattern.command.dinner.java.order.BurgerAndFriesOrder;
import com.arturfrimu.pattern.command.dinner.java.order.SteakOrder;

public class Dinner {
    public static void main(String[] args) {
        Cook cook = new Cook("PanPan");
        Waitress waitress = new Waitress("Elena");
        Customer customer = new Customer("Igor", waitress);
        BurgerAndFriesOrder burgherAndFries = new BurgerAndFriesOrder("BurgherAndFries", cook);
        customer.createOrder(burgherAndFries);
        customer.hungry();
        SteakOrder steak = new SteakOrder("Steak", cook);
        customer.createOrder(steak);
        customer.hungry();
    }
}