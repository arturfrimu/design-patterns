package com.arturfrimu.pattern.observer.weather.java;

public interface Observer {
    void update(float temp, float humidity, float pressure);
}
