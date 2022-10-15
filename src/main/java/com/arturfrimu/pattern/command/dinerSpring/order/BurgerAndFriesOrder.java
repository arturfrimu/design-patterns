package com.arturfrimu.pattern.command.dinerSpring.order;

import com.arturfrimu.pattern.command.dinerSpring.CookService;
import com.arturfrimu.pattern.command.dinerSpring.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BurgerAndFriesOrder implements Order {
    /**
     * Qualifier value and field name need to be equals to BeanName (burgherCookService)
     */
    @Qualifier("burgherCookService")
    private final CookService burgherCookService;

    public void orderUp() {
        burgherCookService.cook();
    }

    @Override
    public String toString() {
        return "BurgerAndFriesOrder";
    }
}
