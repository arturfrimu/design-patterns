package com.arturfrimu.pattern.command.dinner.spring.order;

import com.arturfrimu.pattern.command.dinner.spring.receiver.CookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SteakOrderCommand implements OrderCommand {

    /**
     * Qualifier value and field name need to be equals to BeanName (steakCookService)
     */
    @Qualifier("steakCookService")
    private final CookService steakCookService;

    @Override
    public void execute() {
        System.out.println("\t- WAITRESS: SteakCook, cook a steak please!");
        steakCookService.cook();
        System.out.println("\t- WAITRESS: Thank you very much! It looks great!");
    }

    @Override
    public String toString() {
        return "Steak";
    }
}
