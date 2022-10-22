package com.huckleberrycalfinn.commandpattern;

import java.util.ArrayList;
import java.util.Locale;

public class CommandPatternApp {
  public static void main(String[] args){
    Light kitchenLight = new Light("KITCHEN_LIGHT");
    Light bedroomLight = new Light("BEDROOM_LIGHT");
    RemoteController remoteController = new RemoteController();
    remoteController.setCommand(0, new LightCommand(kitchenLight));
    remoteController.setCommand(1, new LightCommand(bedroomLight));
    remoteController.executeCommand(0, CommandType.ON);
    remoteController.executeCommand(1, CommandType.ON);
    remoteController.executeCommand(0, CommandType.OFF);
    remoteController.executeCommand(1, CommandType.OFF);
  }
}

class Light {
  private String name;
  public Light(String lightName){
    this.name = lightName;
  }
  public void lightOn(){
    System.out.println(this.name.toUpperCase() + "TURNING_ON");
  }

  public void lightOff(){
    System.out.println(this.name.toUpperCase() + "_TURNING_OFF");
  }
}

interface Command {
  void execute(CommandType commandType);
}

class LightCommand implements Command {
  private Light light;

  public LightCommand(Light light){
    this.light = light;
  }
  public void execute(CommandType commandType){
    if (commandType.equals(CommandType.ON)){
      this.light.lightOn();
    } else if (commandType.equals(CommandType.OFF)){
      this.light.lightOff();
    }
  }
}

interface Invoker {
  void setCommand(int slot, Command command);
  void executeCommand(int slot, CommandType commandType);
}

class RemoteController implements Invoker {
  private ArrayList<Command> commands = new ArrayList<>();

  public void setCommand(int slot, Command command){
    this.commands.add(slot, command);
  }

  public void executeCommand(int slot, CommandType commandType){
    this.commands.get(slot).execute(commandType);
  }
}

enum CommandType {
  ON,
  OFF
}
