package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.Light;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivingroomLightOnCommand implements Command {
    private final Light light;

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
