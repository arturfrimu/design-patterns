package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Decorator that adds a flat shipping fee, waived when the running total
 * already exceeds the free-shipping threshold. Always adds an adjustment
 * line so the breakdown shows that shipping was considered.
 */
public class ShippingPriceCalculator extends PriceCalculatorDecorator {

    private static final BigDecimal FLAT_FEE = new BigDecimal("9.99");
    private static final BigDecimal FREE_SHIPPING_THRESHOLD = new BigDecimal("100.00");

    public ShippingPriceCalculator(OrderPriceCalculator delegate) {
        super(delegate);
    }

    @Override
    public PriceQuote calculate(Order order) {
        PriceQuote quote = delegate.calculate(order);

        if (quote.total().compareTo(FREE_SHIPPING_THRESHOLD) >= 0) {
            return quote.withAdjustment("shipping-free",
                    BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        }
        return quote.withAdjustment("shipping-flat",
                FLAT_FEE.setScale(2, RoundingMode.HALF_UP));
    }
}
