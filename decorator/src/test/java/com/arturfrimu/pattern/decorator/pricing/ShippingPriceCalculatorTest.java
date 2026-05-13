package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Customer;
import com.arturfrimu.pattern.decorator.domain.CustomerTier;
import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShippingPriceCalculatorTest {

    private final OrderPriceCalculator wrapped = mock(OrderPriceCalculator.class);
    private final ShippingPriceCalculator calculator = new ShippingPriceCalculator(wrapped);

    @Test
    void smallOrdersGetTheFlatShippingFee() {
        Order order = anyOrder();
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("50.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("59.99");
        assertThat(quote.adjustments()).singleElement().satisfies(adj -> {
            assertThat(adj.label()).isEqualTo("shipping-flat");
            assertThat(adj.amount()).isEqualByComparingTo("9.99");
        });
    }

    @Test
    void ordersAtOrAboveThresholdGetFreeShipping() {
        Order order = anyOrder();
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("100.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("100.00");
        assertThat(quote.adjustments()).singleElement().satisfies(adj -> {
            assertThat(adj.label()).isEqualTo("shipping-free");
            assertThat(adj.amount()).isEqualByComparingTo("0.00");
        });
    }

    private static Order anyOrder() {
        return new Order("o-1", new Customer("c-1", CustomerTier.STANDARD), "DE", List.of());
    }
}
