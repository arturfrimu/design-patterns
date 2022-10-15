package com.arturfrimu.pattern.observer.simple.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/observer")
public class ObserverController {
    private final SimpleSubject simpleSubject;

    @Autowired
    private final Map<String, NewsObserver> observers = new HashMap<>();

    @GetMapping("/change")
    public void observe(@RequestParam("value") int value) {
        simpleSubject.setValue(value);
    }

    @GetMapping("/remove")
    public void removeObserver(@RequestParam("observer") String observer) {
        NewsObserver newsObserver = observers.get(observer);
        simpleSubject.removeObserver(newsObserver);
    }

    @GetMapping("/register")
    public void addObserver(@RequestParam("observer") String observer) {
        NewsObserver newsObserver = observers.get(observer);
        simpleSubject.registerObserver(newsObserver);
    }
}
