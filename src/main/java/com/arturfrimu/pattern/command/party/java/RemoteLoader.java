package com.arturfrimu.pattern.command.party.java;

import java.util.Scanner;

public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light1 = new Light("Living Room");
        Light light2 = new Light("Bathroom");

        LightOnCommand lightOnLivingRoom = new LightOnCommand(light1);
        LightOffCommand lightOffLivingRoom = new LightOffCommand(light1);

        LightOnCommand lightOnBathroom = new LightOnCommand(light2);
        LightOffCommand lightOffBathroom = new LightOffCommand(light2);

        remoteControl.setCommand(0, lightOnLivingRoom, lightOffLivingRoom);
        remoteControl.setCommand(1, lightOnBathroom, lightOffBathroom);

        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String input = sc.nextLine();
            switch (input) {
                case "llon":
                    remoteControl.onButtonWasPushed(0);
                    break;
                case "lloff":
                    remoteControl.offButtonWasPushed(0);
                    break;
                case "bon":
                    remoteControl.onButtonWasPushed(1);
                    break;
                case "boff":
                    remoteControl.offButtonWasPushed(1);
                    break;
                default:
                    active = false;
            }
        }
    }

    public void main1(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light("Living Room");
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);

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

//        System.out.println(remoteControl);
        System.out.println("\n");
        remoteControl.setCommand(4, macro1, macro2);
        remoteControl.onButtonWasPushed(4);
        remoteControl.offButtonWasPushed(4);

//        System.out.println(remoteControl);
    }
}
