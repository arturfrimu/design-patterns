package com.arturfrimu.pattern.command.party.java;

public class LivingroomLightOffCommand implements Command {
    Light light;

    public LivingroomLightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}
