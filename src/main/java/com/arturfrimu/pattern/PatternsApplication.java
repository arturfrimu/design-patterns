package com.arturfrimu.pattern;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PatternsApplication {
    public static void main(String[] args) {
//        SpringApplication.run(PatternsApplication.class, args);
        Credit credit = new Credit(1L, LocalDate.now(), LocalDate.now().plusDays(30), 10000, 3);
        System.out.println(credit.calculateSum());
        System.out.println(credit.calculateSumOver());
    }
}
