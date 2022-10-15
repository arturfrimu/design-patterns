package com.arturfrimu.pattern.observer.simple.spring;

public interface Subject {
    void registerObserver(NewsObserver o);

    void removeObserver(NewsObserver o);

    void notifyObservers();
}
