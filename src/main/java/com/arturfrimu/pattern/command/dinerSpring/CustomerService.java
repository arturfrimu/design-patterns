package com.arturfrimu.pattern.command.dinerSpring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final WaitressService waitress;
    private Order order;

    public void createOrder(Order order) {
        System.out.println("Customer.createOrder(" + order + ")...");
        this.order = order;
    }

    public void hungry() {
        System.out.println("CustomerService.hungry()...");
        waitress.takeOrder(order);
    }

    @Override
    public String toString() {
        return "Customer";
    }
}