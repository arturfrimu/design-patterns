package com.arturfrimu.pattern.observer.simple.java;

public class SecondObserver implements Observer {
    private int value;
    private final Subject simpleSubject;

    public SecondObserver(Subject simpleSubject) {
        this.simpleSubject = simpleSubject;
        simpleSubject.registerObserver(this);
    }

    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("Second Observer value: " + value);
    }
}
