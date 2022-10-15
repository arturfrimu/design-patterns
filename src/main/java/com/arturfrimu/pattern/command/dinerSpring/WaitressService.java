package com.arturfrimu.pattern.command.dinerSpring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitressService {

    public void takeOrder(Order order) {
        System.out.println("Waitress.takeOrder(" + order + ")...");
        order.orderUp();
    }

    @Override
    public String toString() {
        return "WaitressService";
    }
}