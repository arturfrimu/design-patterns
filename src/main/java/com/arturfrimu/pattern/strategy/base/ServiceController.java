package com.arturfrimu.pattern.strategy.base;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ServiceController {

    private final BaseServiceStrategyStorage baseServiceStrategyStorage;

    @GetMapping("/serve")
    public String serve(@RequestParam String serviceType) {
        var service = baseServiceStrategyStorage.get(serviceType);
        return service != null ? service.serve() : "Invalid service type";
    }

    @GetMapping("/services")
    public Set<String> getAvailableServices() {
        return baseServiceStrategyStorage.getAll();
    }
}
