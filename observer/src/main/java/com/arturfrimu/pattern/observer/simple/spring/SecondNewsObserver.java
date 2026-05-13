package com.arturfrimu.pattern.observer.simple.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class SecondNewsObserver implements NewsObserver {
    private int value;
    private final Subject simpleSubject;

    @PostConstruct
    private void init() {
        simpleSubject.registerObserver(this);
    }

    @Override
    public void update(int value) {
        this.value = value;
        display();
    }

    public void display() {
        System.out.println("Second Observer value: " + value);
    }
}
