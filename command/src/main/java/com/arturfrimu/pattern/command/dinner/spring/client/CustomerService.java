package com.arturfrimu.pattern.command.dinner.spring.client;

import com.arturfrimu.pattern.command.dinner.spring.order.OrderCommand;
import com.arturfrimu.pattern.command.dinner.spring.invoker.WaitressService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Think to do it prototype
 */
@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CustomerService {
    private final WaitressService waitress;
    private OrderCommand[] orderCommand;

    public void createOrder(OrderCommand... orderCommand) {
        List<String> orderNames = Arrays.stream(orderCommand).map(OrderCommand::toString).collect(Collectors.toList());
        System.out.print("- CUSTOMER: I want to order the following: [ " + String.join(", ", orderNames) + " ]\n");
        this.orderCommand = orderCommand;
    }

    public void hungry() {
        waitress.takeOrder(orderCommand);
        System.out.println("- CUSTOMER: Thank you very much, it was delicious!");
    }

    @Override
    public String toString() {
        return "Customer";
    }
}