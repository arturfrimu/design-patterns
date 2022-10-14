package com.arturfrimu.pattern.strategy;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class Credit {
    private final long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Double percent;
    private final Double sum;
    private SumBehavior sumCalculator;

    public double calculateSum() {
        return sumCalculator.calculateSum(sum, percent);
    }

    public double calculateSumOver() {
        return sumCalculator.calculateSumOver(sum, percent);
    }

    public void setSumCalculator(SumBehavior sumCalculator) {
        this.sumCalculator = sumCalculator;
    }
}
