package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Decorator that adds Value Added Tax based on the order's country code.
 * Countries with a zero rate do not produce an adjustment line.
 */
public class VatPriceCalculator extends PriceCalculatorDecorator {

    private static final Map<String, BigDecimal> VAT_RATES = Map.of(
            "DE", new BigDecimal("0.19"),
            "FR", new BigDecimal("0.20"),
            "IT", new BigDecimal("0.22"),
            "RO", new BigDecimal("0.19"),
            "US", BigDecimal.ZERO
    );

    public VatPriceCalculator(OrderPriceCalculator delegate) {
        super(delegate);
    }

    @Override
    public PriceQuote calculate(Order order) {
        PriceQuote quote = delegate.calculate(order);

        BigDecimal rate = VAT_RATES.getOrDefault(order.country(), BigDecimal.ZERO);
        if (rate.signum() == 0) {
            return quote;
        }

        BigDecimal vat = quote.total()
                .multiply(rate)
                .setScale(2, RoundingMode.HALF_UP);
        return quote.withAdjustment("vat-" + order.country(), vat);
    }
}
