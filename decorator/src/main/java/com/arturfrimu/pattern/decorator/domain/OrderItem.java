package com.arturfrimu.pattern.decorator.domain;

import java.math.BigDecimal;

public record OrderItem(String sku, BigDecimal unitPrice, int quantity) {

    public BigDecimal lineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
