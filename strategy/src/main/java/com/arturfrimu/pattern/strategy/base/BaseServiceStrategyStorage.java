package com.arturfrimu.pattern.strategy.base;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BaseServiceStrategyStorage {

    private final Map<String, BaseService> services;

    public BaseService get(String name) {
        return services.get(name);
    }

    public Set<String> getAll() {
        return services.keySet();
    }
}