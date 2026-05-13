package com.arturfrimu.pattern.command.party.spring;

import com.arturfrimu.pattern.command.party.spring.command.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/command/macro")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RemoteLoader {
    private final RemoteControl remoteControl;

    @Autowired
    private final Map<String, Command> commands = new HashMap<>();

// http://localhost:8080/command/macro?
// onCommand=hottubOnCommand&
// offCommand=lightOffCommand
// onCommand=lightOnCommand
// &offCommand=hottubOffCommand&

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<String> addMacroCommand(@RequestParam("slot") SLOT slot, @RequestParam("onCommand") String[] onCommand, @RequestParam("offCommand") String[] offCommand) {
        Command[] onCommands = getCommandComponentsByName(onCommand);
        Command[] offCommands = getCommandComponentsByName(offCommand);
        remoteControl.setCommand(SLOT.getValue(slot), new MacroCommand(onCommands), new MacroCommand(offCommands));
        return ResponseEntity.ok("Success");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/add")
    public ResponseEntity<String> addCommand(@RequestParam("slot") SLOT slot, @RequestParam("onCommand") String onCommand, @RequestParam("offCommand") String offCommand) {
        remoteControl.setCommand(SLOT.getValue(slot), commands.get(onCommand), commands.get(offCommand));
        return ResponseEntity.ok("Success");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/buttonWasPushed")
    public boolean lightOn(@RequestParam("slot") SLOT slot, @RequestParam("onOff") boolean onOff) {
        if (onOff) remoteControl.onButtonWasPushed(SLOT.getValue(slot));
        else remoteControl.offButtonWasPushed(SLOT.getValue(slot));
        return !onOff;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/add/on")
    public String addOnCommands(@RequestParam("onCommand") String[] onCommand) {
        Command[] onCommands = getCommandComponentsByName(onCommand);
        remoteControl.setCommand(0, new MacroCommand(onCommands), null);
        return "/add/on";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/add/off")
    public String addOffCommands(@RequestParam("offCommand") String[] offCommand) {
        Command[] offCommands = getCommandComponentsByName(offCommand);
        remoteControl.setCommand(0, null, new MacroCommand(offCommands));
        return "/add/off";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/remove/on")
    public void removeOnCommands(@RequestParam("onCommand") String[] onCommand) {
        Command[] onCommands = getCommandComponentsByName(onCommand);
        // TODO: 16/10/2022
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/remove/off")
    public void removeOffCommands(@RequestParam("offCommand") String[] offCommand) {
        Command[] onCommands = getCommandComponentsByName(offCommand);
        // TODO: 16/10/2022
    }

    private Command[] getCommandComponentsByName(String[] commandsName) {
        Command[] onCommands = new Command[commandsName.length];
        for (int i = 0; i < commandsName.length; i++) {
            Command command = commands.get(commandsName[i]);
            onCommands[i] = command;
        }
        return onCommands;
    }

    // http://localhost:8080/command/macro?onCommand=tvOnCommand&onCommand=hottubOnCommand&onCommand=lightOnCommand&offCommand=hottubOffCommand&offCommand=lightOffCommand&offCommand=tvOffCommand
    // http://localhost:8080/command/macro?onCommand=tvOnCommand

    /*

hottubOnCommand
lightOnCommand
livingroomLightOnCommand
stereoOnCommand
tvOnCommand

hottubOffCommand
lightOffCommand
livingroomLightOffCommand
stereoOffCommand
tvOffCommand

*/
}
