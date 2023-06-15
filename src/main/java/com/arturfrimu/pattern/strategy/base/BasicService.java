package com.arturfrimu.pattern.strategy.base;


import org.springframework.stereotype.Service;

@Service("basic")
class BasicService implements BaseService {
    @Override
    public String serve() {
        return "Basic service";
    }
}
