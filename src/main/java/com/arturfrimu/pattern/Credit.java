package com.arturfrimu.pattern;

import java.time.LocalDate;

public class Credit {
    private final long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private double percent;
    private double sum;

    public Credit(long id, LocalDate startDate, LocalDate endDate, double sum, double percent) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sum = sum;
        this.percent = percent;
    }

    public double calculateSum() {
        return sum + (sum * (percent / 100));
    }

    public double calculateSumOver() {
        return sum * (percent / 100);
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
