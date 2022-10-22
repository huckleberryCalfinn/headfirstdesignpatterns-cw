package com.huckleberrycalfinn.strategy_and_observer_and_unnecessary_genericism;

import java.util.ArrayList;
import java.util.List;

public class MoveStrategyApp {
	public static void main(String[] args){
	  GameAnimator<GameCharacter> gameAnimator = new GameAnimator<>();
	  Person<GameAnimator> jed = new Person.builder().name("jed barlett").build();
	  Person<GameAnimator> toby = new Person.builder().name("toby ziegler").build();
	  gameAnimator.addObserver(jed);
	  gameAnimator.addObserver(toby);
	  gameAnimator.getAnimationFrame();
	}
}



interface Observer<S extends Subject> {
  public void subscribe(S subject);
  public void update();
}

interface Subject<O extends Observer> {
  public void addObserver(O o);
  public void removeObserver(O o);
  public void notifyObservers();
}

interface MoveBehavior {
  public void doMove();
}

interface GameCharacter<S extends Subject> extends Observer<S>, MoveBehavior {}


class Person<S extends Subject> implements GameCharacter<S> {
  private S subject;
  private final String name;

  private Person(String name){
	this.name = name;
  }

  public String getName(){
	return this.name;
  }


  public void doMove(){
	System.out.println("Person(name=" + this.getName() + ")_MOVING");
  }



  public void subscribe(S subject){
	this.subject = subject;
  }

  public void update(){
	this.doMove();
  }


  static class builder {
	public String name;
	public builder(){}

	public builder name(String name) {
	  this.name = name;
	  return this;
	}

	public Person build(){
	  return new Person(this.name);
	}
  }
}

class GameAnimator<O extends Observer> implements Subject<O> {
  private List<O> characters;

  public GameAnimator(){
	this.characters = new ArrayList<O>();
  }

  public void addObserver(O character){
	character.subscribe(this);
	this.characters.add(character);
  }
  public void removeObserver(O character){
	this.characters.remove(character);
  }
  public void notifyObservers() {
	for (O character: this.characters) {
	  character.update();
	}
  }
	public void getAnimationFrame(){
	  this.notifyObservers();
	}
}


class Bird implements MoveBehavior {
  public void doMove(){
	System.out.println("BIRD_MOVING");
  }
}
