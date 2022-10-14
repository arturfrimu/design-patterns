package com.arturfrimu.pattern.strategy.service;

import com.arturfrimu.pattern.strategy.SumStrategy;
import org.springframework.stereotype.Service;

@Service
public class FirstStrategy implements SumStrategy {
    @Override
    public double calculateSum(Double sum, Double percent) {
        return sum + (sum * (percent / 100));
    }

    @Override
    public double calculateSumOver(Double sum, Double percent) {
        return sum * (percent / 100);
    }
}
