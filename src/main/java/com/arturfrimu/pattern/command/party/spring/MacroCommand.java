package com.arturfrimu.pattern.command.party.spring;

import com.arturfrimu.pattern.command.party.spring.command.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MacroCommand implements Command {
    private Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    /**
     * NOTE:  these commands have to be done backwards to ensure
     * proper undo functionality
     */
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}

