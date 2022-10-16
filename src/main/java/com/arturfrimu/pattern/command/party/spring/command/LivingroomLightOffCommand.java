package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.Light;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivingroomLightOffCommand implements Command {
    private final Light light;

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}
