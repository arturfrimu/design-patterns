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

class VatPriceCalculatorTest {

    private final OrderPriceCalculator wrapped = mock(OrderPriceCalculator.class);
    private final VatPriceCalculator calculator = new VatPriceCalculator(wrapped);

    @Test
    void germanOrdersGetNineteenPercentVat() {
        Order order = orderFor("DE");
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("100.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("119.00");
        assertThat(quote.adjustments()).singleElement().satisfies(adj -> {
            assertThat(adj.label()).isEqualTo("vat-DE");
            assertThat(adj.amount()).isEqualByComparingTo("19.00");
        });
    }

    @Test
    void italianOrdersGetTwentyTwoPercentVat() {
        Order order = orderFor("IT");
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("50.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("61.00");
        assertThat(quote.adjustments()).singleElement().satisfies(adj -> {
            assertThat(adj.label()).isEqualTo("vat-IT");
            assertThat(adj.amount()).isEqualByComparingTo("11.00");
        });
    }

    @Test
    void usOrdersHaveNoVatAndNoAdjustmentLine() {
        Order order = orderFor("US");
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("100.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("100.00");
        assertThat(quote.adjustments()).isEmpty();
    }

    @Test
    void unknownCountriesGetNoVat() {
        Order order = orderFor("XX");
        when(wrapped.calculate(order)).thenReturn(PriceQuote.of(new BigDecimal("100.00")));

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.total()).isEqualByComparingTo("100.00");
        assertThat(quote.adjustments()).isEmpty();
    }

    private static Order orderFor(String country) {
        return new Order("o-1", new Customer("c-1", CustomerTier.STANDARD), country, List.of());
    }
}
