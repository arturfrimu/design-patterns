package com.arturfrimu.pattern.command.dinner.spring.receiver;

import org.springframework.stereotype.Service;

@Service
public class SteakCookService implements CookService {

    @Override
    public void cook() {
        System.out.println("\t\tSTEAK COOK: - I will cook this stake with love!");
    }

    @Override
    public String toString() {
        return "SteakCookService";
    }
}