package com.arturfrimu.pattern;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PatternsApplication {
    public static void main(String[] args) {
//        SpringApplication.run(PatternsApplication.class, args);
        Credit credit = Credit.builder()
                .id(1L)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(12))
                .sum(10000d)
                .percent(3d)
                .sumCalculator(new SumBehavior.Base())
                .build();

        System.out.println(credit.calculateSum());
        System.out.println(credit.calculateSumOver());

        credit.setSumCalculator(new SumBehavior.Dupplicate());

        System.out.println(credit.calculateSum());
        System.out.println(credit.calculateSumOver());
    }
}
