//package com.huckleberrycalfinn.observer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SimpleObserverExample {
//  public static void main(String[] args){
//    SimpleSubject subj = new SimpleSubject();
//    SimpleObserver obsA = new SimpleObserver("OBSERVER_A");
//    SimpleObserver obsB = new SimpleObserver("OBS_B");
//    subj.addObserver(obsA);
//    subj.addObserver(obsB);
//    SubscriberObserver subsObs = new SubscriberObserver("SUBS_OBS_A");
//    subsObs.subscribe(subj);
//    subj.setValue(10);
//    subj.setValue(20);
//  }
//}
//
//class SubscriberObserver extends SimpleObserver {
//  public SubscriberObserver(String observerId){
//    super(observerId);
//  }
//  public void subscribe(Subject subject){
//    subject.addObserver(this);
//  }
//}
//
//class SimpleObserver implements Observer {
//  public String observerId;
//
//  public SimpleObserver(String observerId){
//    this.observerId = observerId;
//  }
//  public void update(int val){
//    System.out.println(this.observerId + " received updated value: " + val);
//  }
//}
//
//class SimpleSubject implements Subject {
//  private List<Observer> observers;
//  private int value;
//
//  public SimpleSubject(){
//    this.observers = new ArrayList<Observer>();
//  }
//
//  public int getValue(){
//    return this.value;
//  }
//  public void setValue(int val){
//    this.value = val;
//    this.updateObservers();
//  }
//
//  public void addObserver(Observer o){
//    this.observers.add(o);
//  }
//  public void removeObserver(Observer o){
//    this.observers.remove(o);
//  }
//
//  public void updateObservers(){
//    for (Observer o : this.observers){
//      o.update(this.value);
//    }
//  }
//}
//
//
//interface Subject {
//  public void addObserver(Observer o);
//  public void removeObserver(Observer o);
//  public void updateObservers();
//}
//
//
//interface Observer {
//  public void update(int val);
//}
