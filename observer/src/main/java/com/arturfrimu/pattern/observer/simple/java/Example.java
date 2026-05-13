package com.arturfrimu.pattern.observer.simple.java;

public class Example {
    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();
        FirstObserver firstObserver = new FirstObserver(simpleSubject);
        SecondObserver secondObserver = new SecondObserver(simpleSubject);
        ThirdObserver thirdObserver = new ThirdObserver(simpleSubject);
        simpleSubject.setValue(100);
        System.out.print("\n");

        simpleSubject.removeObserver(firstObserver);
        simpleSubject.setValue(50);
        System.out.print("\n");

        simpleSubject.removeObserver(secondObserver);
        simpleSubject.setValue(25);
    }
}
