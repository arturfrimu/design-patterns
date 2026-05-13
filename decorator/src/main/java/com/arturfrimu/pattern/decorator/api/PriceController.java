package com.arturfrimu.pattern.decorator.api;

import com.arturfrimu.pattern.decorator.domain.Order;
import com.arturfrimu.pattern.decorator.domain.PriceQuote;
import com.arturfrimu.pattern.decorator.pricing.OrderPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

    private final OrderPriceCalculator orderPriceCalculator;

    @PostMapping
    public PriceQuote calculate(@RequestBody Order order) {
        return orderPriceCalculator.calculate(order);
    }
}
