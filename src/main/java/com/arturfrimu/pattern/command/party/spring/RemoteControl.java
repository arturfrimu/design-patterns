package com.arturfrimu.pattern.command.party.spring;

import com.arturfrimu.pattern.command.party.spring.command.Command;
import com.arturfrimu.pattern.command.party.spring.command.NoCommand;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//
// This is the invoker
//
@Service
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    @PostConstruct
    private void init() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    public String toString() {
        StringBuilder stringBuff = new StringBuilder();
        stringBuff.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot ").append(i).append("] ").append(onCommands[i].getClass().getName()).append("    ").append(offCommands[i].getClass().getName()).append("\n");
        }
        stringBuff.append("[undo] ").append(undoCommand.getClass().getName()).append("\n");
        return stringBuff.toString();
    }
}
