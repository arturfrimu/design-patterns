package com.arturfrimu.pattern.observer.weather.java;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
