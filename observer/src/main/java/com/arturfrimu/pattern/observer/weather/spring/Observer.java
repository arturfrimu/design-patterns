package com.arturfrimu.pattern.observer.weather.spring;

public interface Observer {
    void update(float temp, float humidity, float pressure);
}
