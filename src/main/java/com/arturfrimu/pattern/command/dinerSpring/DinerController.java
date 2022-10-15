package com.arturfrimu.pattern.command.dinerSpring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/command")
public class DinerController {
    private final CustomerService customer;

    @Autowired
    private final Map<String, Order> orders = new HashMap<>();

    @GetMapping()
    public void order(@RequestParam("order") String order) {
        Order order1 = orders.get(order);
        customer.createOrder(order1);
        customer.hungry();
    }

    public static void main(String[] args) {

    }
}