
package com.arturfrimu.pattern.decorator;

public abstract class Beverage {
    String description = "Unknown Beverage";

    protected String getDescription() {
        return description;
    }

    public abstract double cost();
}
