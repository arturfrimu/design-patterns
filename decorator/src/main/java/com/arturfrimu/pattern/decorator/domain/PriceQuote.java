package com.arturfrimu.pattern.decorator.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record PriceQuote(
        BigDecimal subtotal,
        List<Adjustment> adjustments,
        BigDecimal total
) {

    public record Adjustment(String label, BigDecimal amount) {
    }

    public static PriceQuote of(BigDecimal subtotal) {
        return new PriceQuote(subtotal, List.of(), subtotal);
    }

    public PriceQuote withAdjustment(String label, BigDecimal amount) {
        List<Adjustment> updated = new ArrayList<>(this.adjustments);
        updated.add(new Adjustment(label, amount));
        return new PriceQuote(this.subtotal, List.copyOf(updated), this.total.add(amount));
    }
}
