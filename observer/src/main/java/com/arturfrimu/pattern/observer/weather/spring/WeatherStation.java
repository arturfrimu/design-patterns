package com.arturfrimu.pattern.observer.weather.spring;

import com.arturfrimu.pattern.observer.simple.spring.NewsObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/observer/weather")
@RequiredArgsConstructor
public class WeatherStation {
    private final WeatherData weatherData;

    @Autowired
    private final Map<String, Observer> observers = new HashMap<>();

    @GetMapping
    public void change() {
        System.out.println("\nFirst measurements start\n");
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("\nFirst measurements end");
        System.out.println("Second measurements start\n");
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("\nSecond measurements end");
        System.out.println("Third measurements start\n");
        weatherData.setMeasurements(78, 90, 29.2f);
        System.out.println("\nThird measurements end");
    }

    @GetMapping("/remove")
    public void removeObserver(@RequestParam("observer") String observer) {
        Observer display = observers.get(observer);
        weatherData.removeObserver(display);
    }

    @GetMapping("/register")
    public void addObserver(@RequestParam("observer") String observer) {
        Observer display = observers.get(observer);
        weatherData.registerObserver(display);
    }

    @GetMapping("/update")
    public String update() {
        return "Temperature: " + weatherData.getTemperature() + " | Humidity: " + weatherData.getHumidity() + " | Pressure: " + weatherData.getPressure();
    }
}

/*

http://localhost:8080/observer/weather

http://localhost:8080/observer/weather/remove?observer=heatIndexDisplay
http://localhost:8080/observer/weather/remove?observer=currentConditionsDisplay

http://localhost:8080/observer/weather
http://localhost:8080/observer/weather/update

http://localhost:8080/observer/weather/register?observer=heatIndexDisplay
http://localhost:8080/observer/weather/register?observer=currentConditionsDisplay

http://localhost:8080/observer/weather
http://localhost:8080/observer/weather/update

*/