package com.arturfrimu.pattern.strategy;

import java.time.LocalDate;

public class StartStrategy {
    public static void main(String[] args) {
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
