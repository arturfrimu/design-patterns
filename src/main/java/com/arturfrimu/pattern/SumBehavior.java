package com.arturfrimu.pattern;

public interface SumBehavior {
    double calculateSum(Double sum, Double percent);
    double calculateSumOver(Double sum, Double percent);

    class Base implements SumBehavior {
        @Override
        public double calculateSum(Double sum, Double percent) {
            return sum + (sum * (percent / 100));
        }

        @Override
        public double calculateSumOver(Double sum, Double percent) {
            return sum * (percent / 100);
        }
    }

    class Dupplicate implements SumBehavior {
        @Override
        public double calculateSum(Double sum, Double percent) {
            return sum * 2;
        }

        @Override
        public double calculateSumOver(Double sum, Double percent) {
            return sum;
        }
    }
}
