package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.Hottub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HottubOffCommand implements Command {
    private final Hottub hottub;

    public void execute() {
        hottub.setTemperature(98);
        hottub.off();
    }

    public void undo() {
        hottub.on();
    }
}
