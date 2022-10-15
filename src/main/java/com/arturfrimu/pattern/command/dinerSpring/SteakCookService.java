package com.arturfrimu.pattern.command.dinerSpring;

import org.springframework.stereotype.Service;

@Service
public class SteakCookService implements CookService {

    @Override
    public void cook() {
        System.out.println("CookService.makeSteak()... Making steak...");
    }

    @Override
    public String toString() {
        return "SteakCookService";
    }
}