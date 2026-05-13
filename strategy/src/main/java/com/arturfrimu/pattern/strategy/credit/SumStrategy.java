package com.arturfrimu.pattern.strategy.credit;

public interface SumStrategy {
    double calculateSum(Double sum, Double percent);
    double calculateSumOver(Double sum, Double percent);
}
