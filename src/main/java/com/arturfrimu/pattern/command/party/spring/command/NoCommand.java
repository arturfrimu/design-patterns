package com.arturfrimu.pattern.command.party.spring.command;

import org.springframework.stereotype.Component;

@Component
public class NoCommand implements Command {
    public void execute() {
    }

    public void undo() {
    }
}
