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

class DiscountPriceCalculatorTest {

    private final OrderPriceCalculator wrapped = mock(OrderPriceCalculator.class);
    private final DiscountPriceCalculator calculator = new DiscountPriceCalculator(wrapped);

    @Test
    void platinumCustomersGetFifteenPercentDiscount() {
        Order order = orderWithTier(CustomerTier.PLATINUM);
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("100.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("85.00");
        assertThat(quote.adjustments()).singleElement().satisfies(adj -> {
            assertThat(adj.label()).isEqualTo("discount-PLATINUM");
            assertThat(adj.amount()).isEqualByComparingTo("-15.00");
        });
    }

    @Test
    void goldCustomersGetTenPercentDiscount() {
        Order order = orderWithTier(CustomerTier.GOLD);
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("200.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("180.00");
        assertThat(quote.adjustments()).singleElement().satisfies(adj -> {
            assertThat(adj.label()).isEqualTo("discount-GOLD");
            assertThat(adj.amount()).isEqualByComparingTo("-20.00");
        });
    }

    @Test
    void standardCustomersGetNoDiscountAndNoAdjustmentLine() {
        Order order = orderWithTier(CustomerTier.STANDARD);
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("100.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("100.00");
        assertThat(quote.adjustments()).isEmpty();
    }

    private static Order orderWithTier(CustomerTier tier) {
        return new Order("o-1", new Customer("c-1", tier), "DE", List.of());
    }
}
