package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.TV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TvOffCommand implements Command {
    private final TV tv;

    public void execute() {
        tv.off();
    }

    public void undo() {
        tv.on();
    }
}
