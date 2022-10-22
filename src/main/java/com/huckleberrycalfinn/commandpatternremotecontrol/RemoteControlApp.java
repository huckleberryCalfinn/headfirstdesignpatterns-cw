package com.huckleberrycalfinn.commandpatternremotecontrol;

import java.util.ArrayList;

public class RemoteControlApp {
  public static void main(String[] args){
	Light kitchenLight = new Light("KITCHEN_LIGHT");
	Light bedroomLight = new Light("BEDROOM_LIGHT");
	GarageDoor garageDoor = new GarageDoor("Bartlet Family Garage");
	RemoteController remoteController = new RemoteController();
	remoteController.setCommand(0, new LightOnCommand(kitchenLight));
	remoteController.setCommand(1, new LightOffCommand(kitchenLight));
	remoteController.setCommand(2, () -> bedroomLight.lightOn());
	remoteController.setCommand(3, () -> bedroomLight.lightOff());
	remoteController.setCommand(4, new OpenGarageDoorCommand(garageDoor));
	remoteController.setCommand(5, new CloseGarageDoorCommand(garageDoor));
	for (int i = 0; i < 6; i++){
	  remoteController.executeCommand(i);
	}
  }
}



class CloseGarageDoorCommand implements Command {
  private GarageDoor garageDoor;
  public CloseGarageDoorCommand(GarageDoor garageDoor){
	this.garageDoor = garageDoor;
  }

  public void execute(){
	this.garageDoor.closeDoor();
  }
}


class OpenGarageDoorCommand implements Command {
  private GarageDoor garageDoor;
  public OpenGarageDoorCommand(GarageDoor garageDoor){
	this.garageDoor = garageDoor;
  }

  public void execute(){
	this.garageDoor.openDoor();
  }
}

class GarageDoor {
  private String name;
  public GarageDoor(String name){
	this.name = name;
  }
  public void openDoor(){
	System.out.println(this.name + " - garage door opened.");
  }

  public void closeDoor(){
	System.out.println(this.name + " - garage door closed.");
  }
}


class RemoteController implements Invoker {
  private ArrayList<Command> commandSlots;

  public RemoteController(){
	this.commandSlots = new ArrayList<Command>();
  }
  public void setCommand(int slot, Command command){
	this.commandSlots.add(slot, command);
  }
  public void executeCommand(int slot){
	this.commandSlots.get(slot).execute();
  }
}

class LightOffCommand implements Command {
  private Light light;
  public LightOffCommand(Light light){
	this.light = light;
  }
  public void execute(){
	this.light.lightOff();
  }
}


class LightOnCommand implements Command {
  private Light light;
  public LightOnCommand(Light light){
	this.light = light;
  }
  public void execute(){
	this.light.lightOn();
  }
}


class Light {
  private String lightName;

  public Light(String lightName){
	this.lightName = lightName;
  }

  public void lightOn(){
	System.out.println(this.lightName + " has been turned on.");
  }

  public void lightOff(){
	System.out.println(this.lightName + " has been turned off");
  }
}


interface Command {
  void execute();
}

interface Invoker {
  void setCommand(int slot, Command command);
  void executeCommand(int slot);
}
