package com.huckleberrycalfinn.compoundpatterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DuckSimulator {
  public static void main(String[] args){
	DuckSimulator simulator = new DuckSimulator();
	QuackableFactory simpleFactory = new SimpleQuackableFactory();
	QuackableFactory countedFactory = new CountedQuackableFactory();
	QuackableFactory observedAndCountedQuackableFactory = new ObservedAndCountedQuackableFactory(new Quackologist());
	simulator.simulate(countedFactory);
	simulator.simulate(simpleFactory);
	simulator.simulate(observedAndCountedQuackableFactory);
  }

  public void simulate(QuackableFactory factory){
	Quackable mallard0 = factory.createMallardDuck();
	Quackable mallard1 = factory.createMallardDuck();
	Quackable mallard2 = factory.createMallardDuck();
	Quackable mallard3 = factory.createMallardDuck();
	Quackable mallard4 = factory.createMallardDuck();
	Quackable redheadDuck = factory.createRedheadDuck();
	Quackable duckCall = factory.createDuckCall();
	Quackable rubberDuck = factory.createRubberDuck();
	Quackable gooseDuck = factory.createGooseDuck();
	Flock flockOfMallards = new Flock();
	flockOfMallards.addAll(mallard0, mallard1, mallard2, mallard3, mallard4);
	Flock flockOfQuackables = new Flock();
	flockOfQuackables.addAll(mallard0, redheadDuck, duckCall, rubberDuck, gooseDuck, flockOfMallards);
	this.simulate(flockOfMallards);
	this.simulate(flockOfQuackables);
	System.out.println(QuackCounter.getNumberOfQuacks());
  }

  public void simulate(Quackable quackable){
	quackable.quack();
  }
}


class Flock implements Quackable {
  private List<Quackable> quackables;
  public Flock(){
	this.quackables = new ArrayList<>();
  }
  public void add(Quackable quackable){
	this.quackables.add(quackable);
  }
  public void addAll(Quackable... quackables){
	for (Quackable quackable : quackables){
	  this.quackables.add(quackable);
	}
  }
  public void quack(){
	Iterator<Quackable> quackableIterator = this.quackables.iterator();
	while (quackableIterator.hasNext()){
	  quackableIterator.next().quack();
	}
  }
  public void registerObserver(Observer o){
	Iterator<Quackable> quackableIterator = this.quackables.iterator();
	while (quackableIterator.hasNext()){
	  quackableIterator.next().registerObserver(o);
	}
  }
  public void notifyObservers(){
	Iterator<Quackable> quackableIterator = this.quackables.iterator();
	while (quackableIterator.hasNext()){
	  quackableIterator.next().notifyObservers();
	}
  }

}

class ObservedAndCountedQuackableFactory implements QuackableFactory {
  private Observer o;
  private QuackableFactory countedQuackableFactory;
  public ObservedAndCountedQuackableFactory(Observer o){
  	this.o = o;
	this.countedQuackableFactory = new CountedQuackableFactory();
  }

  @Override
  public Quackable createMallardDuck() {
	Quackable quackable = this.countedQuackableFactory.createMallardDuck();
	quackable.registerObserver(o);
	return quackable;
  }

  @Override
  public Quackable createRedheadDuck() {
	Quackable quackable = this.countedQuackableFactory.createRedheadDuck();
	quackable.registerObserver(o);
	return quackable;
  }

  @Override
  public Quackable createRubberDuck() {
	Quackable quackable = this.countedQuackableFactory.createRubberDuck();
	quackable.registerObserver(o);
	return quackable;
  }

  @Override
  public Quackable createDuckCall() {
	Quackable quackable = this.countedQuackableFactory.createDuckCall();
	quackable.registerObserver(o);
	return quackable;
  }

  @Override
  public Quackable createGooseDuck() {
	Quackable quackable = this.countedQuackableFactory.createGooseDuck();
	quackable.registerObserver(o);
	return quackable;
  }
}

class CountedQuackableFactory implements QuackableFactory {
  @Override
  public Quackable createMallardDuck() {
	return new QuackCounter(new MallardDuck());
  }

  @Override
  public Quackable createRedheadDuck() {
	return new QuackCounter( new RedheadDuck());
  }

  @Override
  public Quackable createRubberDuck() {
	return new QuackCounter(new RubberDuck());
  }

  @Override
  public Quackable createDuckCall() {
	return new QuackCounter(new DuckCall());
  }

  @Override
  public Quackable createGooseDuck() {
	return new QuackCounter(new GooseAdapter(new Goose()));
  }

}

class SimpleQuackableFactory implements QuackableFactory {
  @Override
  public Quackable createMallardDuck() {
	return new MallardDuck();
  }

  @Override
  public Quackable createRedheadDuck() {
	return new RedheadDuck();
  }

  @Override
  public Quackable createRubberDuck() {
	return new RubberDuck();
  }

  @Override
  public Quackable createDuckCall() {
	return new DuckCall();
  }

  @Override
  public Quackable createGooseDuck() {
	return new GooseAdapter(new Goose());
  }
}

interface QuackableFactory {
  Quackable createMallardDuck();
  Quackable createRedheadDuck();
  Quackable createRubberDuck();
  Quackable createDuckCall();
  Quackable createGooseDuck();
}

class QuackCounter implements Quackable {
  private Quackable quackable;
  private static int numberOfQuacks;

  public QuackCounter(Quackable quackable){
	this.quackable = quackable;
  }
  public void quack(){
	this.quackable.quack();
	QuackCounter.numberOfQuacks++;
  }
  public static int getNumberOfQuacks(){
	return QuackCounter.numberOfQuacks;
  }

  @Override
  public void registerObserver(Observer o) {
  	this.quackable.registerObserver(o);
  }

  @Override
  public void notifyObservers() {
  	this.quackable.notifyObservers();
  }
}

class GooseAdapter implements Quackable {
  private Goose goose;
  private Observable quackObservable;

  public GooseAdapter(Goose goose){
	this.goose = goose;
	this.quackObservable = new QuackObservable(this);
  }
  public void quack(){
	this.goose.honk();
	notifyObservers();
  }
  public void registerObserver(Observer o){
	this.quackObservable.registerObserver(o);
  }
  public void notifyObservers(){
	this.quackObservable.notifyObservers();
  }
}

class Goose  {
  public void honk(){
	System.out.println("honk!");
  }
}

class RubberDuck implements Quackable {
  Observable quackObservable;
  public RubberDuck(){
	this.quackObservable = new QuackObservable(this);
  }
  public void quack(){
	System.out.println("squeak");
	notifyObservers();
  }
  public void registerObserver(Observer o){
	this.quackObservable.registerObserver(o);
  }
  public void notifyObservers(){
	this.quackObservable.notifyObservers();
  }
}


class DuckCall implements Quackable {
  Observable quackObservable;
  public DuckCall(){
	this.quackObservable = new QuackObservable(this);
  }
  public void quack(){
	System.out.println("kwack!");
	notifyObservers();
  }
  public void registerObserver(Observer o){
	this.quackObservable.registerObserver(o);
  }
  public void notifyObservers(){
	this.quackObservable.notifyObservers();
  }
}

class MallardDuck implements Quackable {
  Observable quackObservable;
  public MallardDuck(){
	this.quackObservable = new QuackObservable(this);
  }
  public void quack(){
	System.out.println("quack!");
	notifyObservers();
  }
  public void registerObserver(Observer o){
	this.quackObservable.registerObserver(o);
  }
  public void notifyObservers(){
	this.quackObservable.notifyObservers();
  }
}


class RedheadDuck implements Quackable {
  Observable quackObservable;
  public RedheadDuck(){
	this.quackObservable = new QuackObservable(this);
  }
  public void quack(){
	System.out.println("quack!");
	notifyObservers();
  }
  public void registerObserver(Observer o){
	this.quackObservable.registerObserver(o);
  }
  public void notifyObservers(){
	this.quackObservable.notifyObservers();
  }
}

interface Quackable extends Observable {
  void quack();
}

class QuackObservable implements Observable {
  private Quackable quackable;
  private List<Observer> observers;

  public QuackObservable(Quackable quackable){
	this.quackable = quackable;
	this.observers = new ArrayList<>();
  }

  public void registerObserver(Observer o){
	this.observers.add(o);
  }

  public void notifyObservers(){
	for (Observer o : this.observers){
	  o.update(this.quackable);
	}
  }
}

interface Observable {
  void registerObserver(Observer o);
  void notifyObservers();
}


interface Observer {
  void update(Quackable quackable);
}

class Quackologist implements Observer {
  public void update(Quackable quackable){
	System.out.println("Quackologist - 'i observe:" + quackable + " just quacked.'");
  }
}
