package com.arturfrimu.pattern.decorator.config;

import com.arturfrimu.pattern.decorator.pricing.BaseOrderPriceCalculator;
import com.arturfrimu.pattern.decorator.pricing.DiscountPriceCalculator;
import com.arturfrimu.pattern.decorator.pricing.OrderPriceCalculator;
import com.arturfrimu.pattern.decorator.pricing.ShippingPriceCalculator;
import com.arturfrimu.pattern.decorator.pricing.VatPriceCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Wires the decorator chain explicitly.
 * <p>
 * Read inside-out: the base calculator is wrapped by Discount, that quote
 * is then wrapped by VAT (so VAT is applied to subtotal-discount) and
 * finally by Shipping (so the free-shipping threshold is evaluated
 * against the subtotal-discount+VAT total).
 */
@Configuration
public class PricingConfiguration {

    @Bean
    @Primary
    public OrderPriceCalculator orderPriceCalculator(BaseOrderPriceCalculator base) {
        OrderPriceCalculator withDiscount = new DiscountPriceCalculator(base);
        OrderPriceCalculator withVat = new VatPriceCalculator(withDiscount);
        return new ShippingPriceCalculator(withVat);
    }
}
