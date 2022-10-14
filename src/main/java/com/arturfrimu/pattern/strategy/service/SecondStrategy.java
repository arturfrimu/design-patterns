package com.arturfrimu.pattern.strategy.service;

import com.arturfrimu.pattern.strategy.SumStrategy;
import org.springframework.stereotype.Service;

@Service
public class SecondStrategy implements SumStrategy {
    @Override
    public double calculateSum(Double sum, Double percent) {
        return sum * 2;
    }

    @Override
    public double calculateSumOver(Double sum, Double percent) {
        return sum;
    }
}
