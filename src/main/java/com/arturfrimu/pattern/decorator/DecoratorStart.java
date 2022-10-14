package com.arturfrimu.pattern.decorator;

public class DecoratorStart {
    public static void main(String args[]) {
        Beverage beverage = new Mocha(new Mocha(new Mocha(new Milk(new Espresso()))));
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
        Beverage beverage2 = new DarkRoast();
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
        Beverage beverage3 = new HouseBlend();
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}

