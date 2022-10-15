package com.arturfrimu.pattern.command.dinner.spring.order;

@FunctionalInterface
public interface OrderCommand {
    void execute();
}
