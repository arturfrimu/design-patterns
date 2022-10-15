package com.arturfrimu.pattern.observer.simple.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleSubject implements Subject {
    private final List<NewsObserver> newsObservers = new ArrayList<>();
    private int value = 0;

    @Override
    public void registerObserver(NewsObserver o) {
        newsObservers.add(o);
    }

    @Override
    public void removeObserver(NewsObserver o) {
        newsObservers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (NewsObserver newsObserver : newsObservers) {
            newsObserver.update(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }
}