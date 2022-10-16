package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.Hottub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HottubOnCommand implements Command {
    private final Hottub hottub;

    public void execute() {
        hottub.on();
        hottub.setTemperature(104);
        hottub.circulate();
    }

    public void undo() {
        hottub.off();
    }
}
