package com.arturfrimu.pattern.command.dinner.spring.receiver;

import org.springframework.stereotype.Service;

@Service
public class BurgherCookService implements CookService {

    @Override
    public void cook() {
        System.out.println("\t\tBURGHER COOC: - I will cook this burgher with love!");
    }

    @Override
    public String toString() {
        return "BurgherCookService";
    }
}