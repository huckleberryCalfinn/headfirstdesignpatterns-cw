package com.oldhuckleberrycalfinn;

public class MainChapter1 {
	public static void main(String[] args){
		NormalDuck normalDuck = new NormalDuck("Norm");
		normalDuck.display();
		normalDuck.performFly();

		normalDuck.setFlyBehavior(new NoFlyBehavior());
		normalDuck.performFly();

		RocketShip rocket = new RocketShip();
		rocket.performFly();
		rocket.setFlyBehavior(new RocketFlyBehavior());
		rocket.performFly();
	}
}


class RocketShip {
  FlyBehavior flyBehavior;

  public RocketShip(){
	this.flyBehavior = new NoFlyBehavior();
  }

  public void setFlyBehavior(FlyBehavior fb){
	this.flyBehavior = fb;
  }

  public void performFly(){
	this.flyBehavior.fly();
  }
}

class RocketFlyBehavior implements FlyBehavior {
  public void fly(){
	System.out.println("flying by rocket thrust-combustion");
  }
}

class NormalDuck extends Duck {
  public NormalDuck(String name){
	this.name = name;
	this.flyBehavior = new NormalFlyBehavior();
  }

  public void display(){
	System.out.println("displaying " + this.name + " duck");
  }

  public void setFlyBehavior(FlyBehavior flyBehavior){
	this.flyBehavior = flyBehavior;
  }
}


abstract class Duck {
  String name;
  FlyBehavior flyBehavior;

  public Duck(){}

  public abstract void display();

  public void performFly(){
	this.flyBehavior.fly();
  }
}


interface FlyBehavior {
  public void fly();
}



class NormalFlyBehavior implements FlyBehavior {
  public void fly(){
	System.out.println("performing normal fly behavior!");
  }
}

class NoFlyBehavior implements FlyBehavior {
  public void fly(){
	System.out.println("no flying");
  }
}
