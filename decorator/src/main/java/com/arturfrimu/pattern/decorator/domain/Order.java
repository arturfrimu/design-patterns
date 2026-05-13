package com.arturfrimu.pattern.decorator.domain;

import java.util.List;

public record Order(
        String id,
        Customer customer,
        String country,
        List<OrderItem> items
) {
}
