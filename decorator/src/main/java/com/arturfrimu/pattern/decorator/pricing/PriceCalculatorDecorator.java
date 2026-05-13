package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;

/**
 * Abstract decorator that holds a reference to the wrapped {@link OrderPriceCalculator}
 * and delegates {@link #calculate(Order)} to it. Concrete decorators override
 * {@link #calculate(Order)} to add their own adjustment on top of the wrapped
 * calculator's result.
 */
public abstract class PriceCalculatorDecorator implements OrderPriceCalculator {

    protected final OrderPriceCalculator delegate;

    protected PriceCalculatorDecorator(OrderPriceCalculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public PriceQuote calculate(Order order) {
        return delegate.calculate(order);
    }
}
