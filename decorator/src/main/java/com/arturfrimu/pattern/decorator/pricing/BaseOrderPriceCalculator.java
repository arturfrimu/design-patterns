package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.OrderItem;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Concrete component of the Decorator pattern: produces the raw subtotal
 * for an order by summing the line totals of each item.
 */
@Component
public class BaseOrderPriceCalculator implements OrderPriceCalculator {

    @Override
    public PriceQuote calculate(Order order) {
        BigDecimal subtotal = order.items().stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
        return PriceQuote.of(subtotal);
    }
}
