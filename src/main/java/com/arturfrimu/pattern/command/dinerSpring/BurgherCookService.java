package com.arturfrimu.pattern.command.dinerSpring;

import org.springframework.stereotype.Service;

@Service
public class BurgherCookService implements CookService {

    @Override
    public void cook() {
        System.out.println("CookService.makeBurger()... Making a burger...");
    }

    @Override
    public String toString() {
        return "BurgherCookService";
    }
}