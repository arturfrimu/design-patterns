package com.arturfrimu.pattern.decorator;

public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;

    @Override
    public double cost() {
        return 0;
    }

    public abstract String getDescription();
}
