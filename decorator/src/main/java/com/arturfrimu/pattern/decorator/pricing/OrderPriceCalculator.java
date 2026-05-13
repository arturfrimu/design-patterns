package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;

/**
 * Component interface of the Decorator pattern.
 * <p>
 * Both the concrete component ({@link BaseOrderPriceCalculator}) and the
 * decorators ({@link PriceCalculatorDecorator}) implement this interface
 * so they can be chained transparently.
 */
public interface OrderPriceCalculator {

    PriceQuote calculate(Order order);
}
