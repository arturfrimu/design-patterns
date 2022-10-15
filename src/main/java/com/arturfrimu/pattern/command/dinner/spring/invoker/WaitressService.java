package com.arturfrimu.pattern.command.dinner.spring.invoker;

import com.arturfrimu.pattern.command.dinner.spring.order.OrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitressService {

    public void takeOrder(OrderCommand... orderCommand) {
        System.out.println("\t- WAITRESS: Ok, thank you for your order!\n");
        for (OrderCommand command : orderCommand) {
            command.execute();
        }
        System.out.println("\n\t- WAITRESS: The dinner is ready, good appetite !");
    }

    @Override
    public String toString() {
        return "WaitressService";
    }
}