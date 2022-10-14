package com.arturfrimu.pattern.strategy;

import lombok.Builder;

@Builder
public class Credit {
    private final long id;
    private final Double percent;
    private final Double sum;
    private SumStrategy sumStrategy;

    public double calculateSum() {
        return sumStrategy.calculateSum(sum, percent);
    }

    public double calculateSumOver() {
        return sumStrategy.calculateSumOver(sum, percent);
    }

    public void setSumStrategy(SumStrategy sumStrategy) {
        this.sumStrategy = sumStrategy;
    }
}
