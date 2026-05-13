package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.CustomerTier;
import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Decorator that applies a percentage discount based on the customer's loyalty tier.
 * Standard customers get no discount and therefore no adjustment line is added.
 */
public class DiscountPriceCalculator extends PriceCalculatorDecorator {

    private static final Map<CustomerTier, BigDecimal> DISCOUNT_RATES = Map.of(
            CustomerTier.STANDARD, BigDecimal.ZERO,
            CustomerTier.GOLD, new BigDecimal("0.10"),
            CustomerTier.PLATINUM, new BigDecimal("0.15")
    );

    public DiscountPriceCalculator(OrderPriceCalculator delegate) {
        super(delegate);
    }

    @Override
    public PriceQuote calculate(Order order) {
        PriceQuote quote = delegate.calculate(order);

        CustomerTier tier = order.customer().tier();
        BigDecimal rate = DISCOUNT_RATES.getOrDefault(tier, BigDecimal.ZERO);
        if (rate.signum() == 0) {
            return quote;
        }

        BigDecimal discount = quote.total()
                .multiply(rate)
                .negate()
                .setScale(2, RoundingMode.HALF_UP);
        return quote.withAdjustment("discount-" + tier.name(), discount);
    }
}
