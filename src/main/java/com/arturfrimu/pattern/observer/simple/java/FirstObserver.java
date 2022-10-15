package com.arturfrimu.pattern.observer.simple.java;

public class FirstObserver implements Observer {
    private int value;
    private final Subject simpleSubject;

    public FirstObserver(Subject simpleSubject) {
        this.simpleSubject = simpleSubject;
        simpleSubject.registerObserver(this);
    }

    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("First Observer value: " + value);
    }
}
