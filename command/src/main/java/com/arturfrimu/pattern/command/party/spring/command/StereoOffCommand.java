package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.Stereo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StereoOffCommand implements Command {
    private final Stereo stereo;

    public void execute() {
        stereo.off();
    }

    public void undo() {
        stereo.on();
    }
}
