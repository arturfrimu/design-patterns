package com.arturfrimu.pattern.observer.weather.spring;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
