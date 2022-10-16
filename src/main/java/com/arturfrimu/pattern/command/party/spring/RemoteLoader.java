package com.arturfrimu.pattern.command.party.spring;

import com.arturfrimu.pattern.command.party.spring.command.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public String test() {
        return "WORK";
    }


// http://localhost:8080/command/macro?onCommand=hottubOnCommand&onCommand=lightOnCommand&offCommand=hottubOffCommand&offCommand=lightOffCommand

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String command(@RequestParam("slot") SLOT slot, @RequestParam("onCommand") String[] onCommand, @RequestParam("offCommand") String[] offCommand) {
        Command[] onCommands = getCommandComponentsByName(onCommand);
        Command[] offCommands = getCommandComponentsByName(offCommand);

        remoteControl.setCommand(SLOT.getValue(slot), new MacroCommand(onCommands), new MacroCommand(offCommands));
        return "add commands";
    }

    public enum SLOT {
        KITCHEN(0), BATHROOM(1);

        private final int value;

        SLOT(int i) {
            this.value = i;
        }

        public static SLOT getValue(String slot) {
            return SLOT.valueOf(slot);
        }

        public static SLOT getValue(int value) {
            return Arrays.stream(SLOT.values()).filter(s -> s.value == value).findFirst().orElse(null);
        }

        public static int getValue(SLOT slot) {
            return slot.value;
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/lightOn")
    public String lightOn(@RequestParam("slot") SLOT slot) {
        remoteControl.onButtonWasPushed(SLOT.getValue(slot));
        return "roomLightOn";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/lightOff")
    public String lightOff(@RequestParam("slot") SLOT slot) {
        remoteControl.offButtonWasPushed(SLOT.getValue(slot));
        return "roomLightOff";
    }

//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping("/bathRoomLightOn")
//    public String bathRoomLightOn(@RequestParam("slot") SLOT slot) {
//        remoteControl.onButtonWasPushed(SLOT.getValue(slot));
//        return "roomLightOn";
//    }
//
//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping("/bathRoomLightOff")
//    public String bathRoomLightOff(@RequestParam("slot") SLOT slot) {
//        remoteControl.offButtonWasPushed(SLOT.getValue(slot));
//        return "roomLightOff";
//    }

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

    private Command[] getCommandComponentsByName(@RequestParam("onCommand") String[] onCommand) {
        Command[] onCommands = new Command[onCommand.length];
        for (int i = 0; i < onCommand.length; i++) {
            Command command = commands.get(onCommand[i]);
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
