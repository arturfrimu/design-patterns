package com.arturfrimu.pattern.command.dinner.spring.controller;

import com.arturfrimu.pattern.command.dinner.spring.client.CustomerService;
import com.arturfrimu.pattern.command.dinner.spring.order.OrderCommand;
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
    private final Map<String, OrderCommand> orders = new HashMap<>();

    @GetMapping()
    public void order(@RequestParam("order") String order) {
        customer.createOrder(orders.get(order));
        customer.hungry();
    }

    @GetMapping("/everything")
    public void giveMeEverything() {
        OrderCommand[] objects = orders.values().toArray(new OrderCommand[0]);
        customer.createOrder(objects);
        customer.hungry();
    }
}