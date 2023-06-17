package com.huckleberrycalfinn.commandpatternremotewithundo;

import java.rmi.Remote;

public class RemoteControlApp {
  public static void main(String[] args){
    Light kitchenLight = new Light("KITCHEN_LIGHT");
    Light bedroomLight = new Light("BEDROOM_LIGHT");
    CeilingFan livingRoomFan = new CeilingFan("LIVING_ROOM_FAN");
    Command kitchenLightOn = new LightOnCommand(kitchenLight);
    Command kitchenLightOff = new LightOffCommand(kitchenLight);
    Command bedroomLightOn = new LightOnCommand(bedroomLight);
    Command bedroomLightOff = new LightOffCommand(bedroomLight);
    Command livingRoomFanHigh = new CeilingFanCommandHigh(livingRoomFan);
    Command livingRoomFanOff = new CeilingFanCommandOff(livingRoomFan);
    Command[] partyOnCommands = {
            kitchenLightOn,
            bedroomLightOn,
            livingRoomFanHigh
    };
    Command[] partyOffCommands = {
      kitchenLightOff,
      bedroomLightOff,
      livingRoomFanOff
    };
    RemoteControl remote = new RemoteControl();
    remote.setCommand(0, new MacroCommand(partyOnCommands), new MacroCommand(partyOffCommands));
    remote.onButtonPushed(0);
    remote.undoButtonPushed();
    remote.onButtonPushed(0);
    remote.offButtonPushed(0);
  }
}


class MacroCommand implements Command {
  Command[] commands;
  public MacroCommand(Command[] commands){
    this.commands = commands;
  }
  public void execute(){
    for (int i = 0; i < commands.length; i++){
      this.commands[i].execute();
    }
  }
  public void undo(){
    for ( int i = 0; i < commands.length; i++){
      this.commands[i].undo();
    }
  }
}


class CeilingFanCommandOff implements Command {
  private CeilingFan fan;
  private int prevSpeed;

  public CeilingFanCommandOff(CeilingFan fan){
    this.fan = fan;
  }

  public void execute(){
    this.prevSpeed = this.fan.getSpeed();
    fan.off();
  }

  public void undo(){
    if (this.prevSpeed == CeilingFan.HIGH) {
      this.fan.high();
    } else if (this.prevSpeed == CeilingFan.LOW){
      this.fan.low();
    } else if (this.prevSpeed == CeilingFan.OFF){
      this.fan.off();
    }
  }
}


class CeilingFanCommandHigh implements Command {
  private CeilingFan fan;
  private int prevSpeed;

  public CeilingFanCommandHigh(CeilingFan fan){
    this.fan = fan;
  }

  public void execute(){
    this.prevSpeed = this.fan.getSpeed();
    fan.high();
  }

  public void undo(){
    if (this.prevSpeed == CeilingFan.HIGH) {
      this.fan.high();
    } else if (this.prevSpeed == CeilingFan.LOW){
      this.fan.low();
    } else if (this.prevSpeed == CeilingFan.OFF){
      this.fan.off();
    }
  }
}

class CeilingFan {
  public static final int HIGH = 2;
  public static final int LOW = 1;
  public static final int OFF = 0;
  private String name;
  private int speed;

  public CeilingFan(String name){
    this.name = name;
    this.speed = OFF;
  }

  public void high(){
    this.speed = HIGH;
    System.out.println(this.name + " - ceiling fan turned to high");
  }
  public void low(){
    this.speed = LOW;
    System.out.println(this.name + " - ceiling fan turned to low");
  }
  public void off(){
    this.speed = OFF;
    System.out.println(this.name + " - ceiling fan turned off");

  }
  public int getSpeed(){
    return this.speed;
  }
}


class LightOffCommand implements Command {
  private Light light;
  public LightOffCommand(Light light){
    this.light = light;
  }
  public void execute(){
    this.light.off();
  }
  public void undo(){
    this.light.on();
  }
}



class LightOnCommand implements Command {
  private Light light;
  public LightOnCommand(Light light){
    this.light = light;
  }
  public void execute(){
    this.light.on();
  }
  public void undo(){
    this.light.off();
  }
}

class Light {
  public String name;
  public Light(String lightName){
    this.name = lightName;
  }
  public void on(){
    System.out.println(this.name + " - light turned on");
  }
  public void off(){
    System.out.println(this.name + " - light turned off");
  }
}

class RemoteControl implements Invoker {
  private Command[] onCommands;
  private Command[] offCommands;
  private Command undoCommand;

  public RemoteControl(){
    this.onCommands = new Command[7];
    this.offCommands = new Command[7];
    Command noCommand = new NoCommand();
    this.undoCommand = noCommand;
    for ( int i = 0; i < 7; i++){
//      this.onCommands[i] = noCommand;
//      this.offCommands[i] = noCommand;
      this.setCommand(i, noCommand, noCommand);
    }
  }

  public void setCommand(int slot, Command onCommand, Command offCommand){
    this.onCommands[slot] = onCommand;
    this.offCommands[slot] = offCommand;
  }

  public void onButtonPushed(int slot){
    this.onCommands[slot].execute();
    this.undoCommand = this.onCommands[slot];
  }
  public void offButtonPushed(int slot){
    this.offCommands[slot].execute();
    this.undoCommand = this.offCommands[slot];
  }
  public void undoButtonPushed(){
    this.undoCommand.undo();
  }
}

class NoCommand implements Command {
  public void execute(){}
  public void undo(){}
}

interface Invoker {
  void setCommand(int slot, Command onCommand, Command offCommand);
  void onButtonPushed(int slot);
  void offButtonPushed(int slot);
  void undoButtonPushed();
}

interface Command {
  void execute();
  void undo();
}
