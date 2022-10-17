package com.arturfrimu.pattern.command.party.java;

public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light1 = new Light("Living Room");
        LightOnCommand lightOn = new LightOnCommand(light1);
        LightOffCommand lightOff = new LightOffCommand(light1);

        //        System.out.println(remoteControl);
        System.out.println("\n");
        remoteControl.setCommand(0, lightOn, lightOff);
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);

        TV tv = new TV("Living Room");
        TVOffCommand tvOff = new TVOffCommand(tv);
        TVOnCommand tvOn = new TVOnCommand(tv);

        //        System.out.println(remoteControl);
        System.out.println("\n");
        remoteControl.setCommand(1, tvOn, tvOff);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);

        Stereo stereo = new Stereo("Living Room");
        StereoOnCommand stereoOn = new StereoOnCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        //        System.out.println(remoteControl);
        System.out.println("\n");
        remoteControl.setCommand(2, stereoOn, stereoOff);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);

        Hottub hottub = new Hottub();
        HottubOnCommand hottubOn = new HottubOnCommand(hottub);
        HottubOffCommand hottubOff = new HottubOffCommand(hottub);

        //        System.out.println(remoteControl);
        System.out.println("\n");
        remoteControl.setCommand(3, hottubOn, hottubOff);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);

        Command[] partyOn = {lightOn, stereoOn, tvOn, hottubOn};
        Command[] partyOff = {lightOff, stereoOff, tvOff, hottubOff};

        MacroCommand macro1 = new MacroCommand(partyOn);
        MacroCommand macro2 = new MacroCommand(partyOff);

        System.out.println(remoteControl);
        System.out.println("\n");
        remoteControl.setCommand(4, macro1, macro2);
        remoteControl.onButtonWasPushed(4);
        remoteControl.offButtonWasPushed(4);

        System.out.println(remoteControl);
    }
}
