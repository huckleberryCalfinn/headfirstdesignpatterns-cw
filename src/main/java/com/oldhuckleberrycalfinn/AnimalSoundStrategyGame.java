package com.oldhuckleberrycalfinn;

public class AnimalSoundStrategyGame {
  public static void main(String[] args){
	Dog d = new Dog();
	Cat c = new Cat();
	d.display();
	d.performSound();
	c.display();
	c.performSound();
  }
}

class Cat extends Animal {
  public Cat(){
	this.soundBehavior = new MeowSoundBehavior();
  }
  public void display(){
	System.out.println("DISPLAY_CAT");
  }
}

class Dog extends Animal {
  public Dog(){
	this.soundBehavior = new BarkSoundBehavior();
  }

  public void display(){
	System.out.println("DISPLAY_DOG");
  }
}


abstract class Animal {
  public SoundBehavior soundBehavior;

  public abstract void display();

  public void performSound(){
	  this.soundBehavior.makeSound();
  }
}


interface SoundBehavior {
  public void makeSound();
}

class BarkSoundBehavior implements SoundBehavior {
  public void makeSound(){
	System.out.println("bark! bark!");
  }
}

class MeowSoundBehavior implements SoundBehavior {
  public void makeSound(){
	System.out.println("meoooooowwww");
  }
}
