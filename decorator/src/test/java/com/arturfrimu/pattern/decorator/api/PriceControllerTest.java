package com.arturfrimu.pattern.decorator.api;

import com.arturfrimu.pattern.decorator.domain.Customer;
import com.arturfrimu.pattern.decorator.domain.CustomerTier;
import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.OrderItem;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestRestTemplate
class PriceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void calculatesFullPriceWithDiscountVatAndShippingForPlatinumGermanOrder() {
        // Subtotal: 2 * 50.00 = 100.00
        // PLATINUM discount: -15% -> -15.00, running total 85.00
        // VAT DE 19% of 85.00 -> +16.15, running total 101.15
        // Shipping: 101.15 >= 100 -> free shipping, +0.00
        Order order = new Order(
                "o-1",
                new Customer("c-1", CustomerTier.PLATINUM),
                "DE",
                List.of(new OrderItem("sku-a", new BigDecimal("50.00"), 2))
        );

        ResponseEntity<PriceQuote> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/price", order, PriceQuote.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        PriceQuote quote = response.getBody();
        assertThat(quote).isNotNull();
        assertThat(quote.subtotal()).isEqualByComparingTo("100.00");
        assertThat(quote.total()).isEqualByComparingTo("101.15");
        assertThat(quote.adjustments())
                .extracting(PriceQuote.Adjustment::label)
                .containsExactly("discount-PLATINUM", "vat-DE", "shipping-free");
        assertThat(quote.adjustments())
                .extracting(PriceQuote.Adjustment::amount)
                .usingElementComparator(BigDecimal::compareTo)
                .containsExactly(
                        new BigDecimal("-15.00"),
                        new BigDecimal("16.15"),
                        new BigDecimal("0.00")
                );
    }

    @Test
    void smallUsStandardOrderHasOnlyFlatShipping() {
        // Subtotal: 1 * 20.00 = 20.00
        // STANDARD: no discount line
        // VAT US: 0% no line
        // Shipping: 20.00 < 100 -> flat 9.99
        Order order = new Order(
                "o-2",
                new Customer("c-2", CustomerTier.STANDARD),
                "US",
                List.of(new OrderItem("sku-b", new BigDecimal("20.00"), 1))
        );

        ResponseEntity<PriceQuote> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/price", order, PriceQuote.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        PriceQuote quote = response.getBody();
        assertThat(quote).isNotNull();
        assertThat(quote.subtotal()).isEqualByComparingTo("20.00");
        assertThat(quote.total()).isEqualByComparingTo("29.99");
        assertThat(quote.adjustments())
                .extracting(PriceQuote.Adjustment::label)
                .containsExactly("shipping-flat");
    }
}
