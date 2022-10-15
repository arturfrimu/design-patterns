package com.arturfrimu.pattern.strategy.credit.controller;

import com.arturfrimu.pattern.strategy.credit.Credit;
import com.arturfrimu.pattern.strategy.credit.SumStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StrategyController {
    @Autowired
    Map<String, SumStrategy> strategies = new HashMap<>();

    @GetMapping()
    public String calculate(@RequestParam("/strategy") String strategy) {
        SumStrategy sumStrategy = strategies.get(strategy);
        Credit build = Credit.builder()
                .id(1L)
                .sumStrategy(sumStrategy)
                .percent(5d)
                .sum(10000d)
                .build();
        double totalToPay = build.calculateSum();
        double payOver = build.calculateSumOver();
        return "You need to pay: " + totalToPay + ". You will pay over : " + payOver + "$";
    }

    @GetMapping("/strategy/compare")
    public String showDifferenceBetween2Strategies(@RequestParam("strategy1") String strategy1,
                                                   @RequestParam("strategy2") String strategy2) {
        Credit credit = Credit.builder()
                .id(1L)
                .sumStrategy(strategies.get(strategy1))
                .percent(5d)
                .sum(10000d)
                .build();

        double totalSumToPayStrategy1 = credit.calculateSum();
        double payOverStrategy1 = credit.calculateSumOver();

        credit.setSumStrategy(strategies.get(strategy2));

        double totalSumToPayStrategy2 = credit.calculateSum();
        double payOverStrategy2 = credit.calculateSumOver();

        return "With strategy " + strategy1 + " you need to pay: " + totalSumToPayStrategy1 + ". You will pay over : " + payOverStrategy1 + "$ "
                + "With strategy " + strategy2 + " you need to pay: " + totalSumToPayStrategy2 + ". You will pay over : " + payOverStrategy2 + "$";
    }
}
