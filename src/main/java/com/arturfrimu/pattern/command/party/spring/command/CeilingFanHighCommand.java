package com.arturfrimu.pattern.command.party.spring.command;

import com.arturfrimu.pattern.command.party.spring.CeilingFan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CeilingFanHighCommand implements Command {
    private final CeilingFan ceilingFan;
    int prevSpeed;

    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
    }

    public void undo() {
        switch (prevSpeed) {
            case CeilingFan.HIGH:
                ceilingFan.high();
                break;
            case CeilingFan.MEDIUM:
                ceilingFan.medium();
                break;
            case CeilingFan.LOW:
                ceilingFan.low();
                break;
            default:
                ceilingFan.off();
                break;
        }
    }
}
