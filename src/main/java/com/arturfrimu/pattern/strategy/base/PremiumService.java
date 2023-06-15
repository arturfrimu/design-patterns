package com.arturfrimu.pattern.strategy.base;


import org.springframework.stereotype.Service;

@Service("premium")
class PremiumService implements BaseService {
    @Override
    public String serve() {
        return "Premium service";
    }
}
