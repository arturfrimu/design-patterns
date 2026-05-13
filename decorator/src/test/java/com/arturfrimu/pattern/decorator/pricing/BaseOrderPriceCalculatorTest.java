package com.arturfrimu.pattern.decorator.pricing;

import com.arturfrimu.pattern.decorator.domain.Customer;
import com.arturfrimu.pattern.decorator.domain.CustomerTier;
import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.OrderItem;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BaseOrderPriceCalculatorTest {

    private final BaseOrderPriceCalculator calculator = new BaseOrderPriceCalculator();

    @Test
    void subtotalIsTheSumOfLineTotals() {
        Order order = new Order(
                "o-1",
                new Customer("c-1", CustomerTier.STANDARD),
                "DE",
                List.of(
                        new OrderItem("sku-a", new BigDecimal("10.00"), 2),
                        new OrderItem("sku-b", new BigDecimal("5.50"), 3)
                )
        );

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.subtotal()).isEqualByComparingTo("36.50");
        assertThat(quote.total()).isEqualByComparingTo("36.50");
        assertThat(quote.adjustments()).isEmpty();
    }

    @Test
    void emptyOrderHasZeroTotal() {
        Order order = new Order(
                "o-2",
                new Customer("c-1", CustomerTier.STANDARD),
                "DE",
                List.of()
        );

        PriceQuote quote = calculator.calculate(order);

        assertThat(quote.subtotal()).isEqualByComparingTo("0.00");
        assertThat(quote.total()).isEqualByComparingTo("0.00");
        assertThat(quote.adjustments()).isEmpty();
    }
}
