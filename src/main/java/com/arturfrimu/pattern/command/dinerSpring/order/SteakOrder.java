package com.arturfrimu.pattern.command.dinerSpring.order;

import com.arturfrimu.pattern.command.dinerSpring.CookService;
import com.arturfrimu.pattern.command.dinerSpring.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SteakOrder implements Order {

    /**
     * Qualifier value and field name need to be equals to BeanName (steakCookService)
     */
    @Qualifier("steakCookService")
    private final CookService steakCookService;

    public void orderUp() {
        steakCookService.cook();
    }

    @Override
    public String toString() {
        return "SteakOrder";
    }
}
