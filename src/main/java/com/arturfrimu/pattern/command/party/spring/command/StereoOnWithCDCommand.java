package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.Stereo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StereoOnWithCDCommand implements Command {
    private final Stereo stereo;

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    public void undo() {
        stereo.off();
    }
}
