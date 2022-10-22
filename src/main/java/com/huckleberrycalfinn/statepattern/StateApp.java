package com.huckleberrycalfinn.statepattern;

public class StateApp {
  public static void main(String[] args){
    GumballMachine machine = new GumballMachine(4);
    for (int i = 0; i < 4; i++){
      machine.insertQuarter();
      machine.ejectQuarter();
      machine.turnCrank();
      machine.insertQuarter();
      machine.turnCrank();
      System.out.println(machine.getGumballCount());
      System.out.println();
    }
  }
}


class GumballMachine {
  private State noQuarterState;
  private State hasQuarterState;
  private State soldState;
  private State soldOutState;
  private State winnerState;
  private int gumballCount = 0;
  public int transactionsCount = 0;
  private State state;

  public GumballMachine(int gumballCount){
    this.gumballCount = gumballCount;
    this.noQuarterState = new NoQuarterState(this);
    this.hasQuarterState = new HasQuarterState(this);
    this.soldState = new SoldState(this);
    this.soldOutState = new SoldOutState(this);
    this.winnerState = new WinnerState(this);
    if (this.gumballCount > 0){
      this.state = this.noQuarterState;
    } else {
      this.state = this.soldOutState;
    }
  }

  public void insertQuarter(){
    this.state.insertQuarter();
  }

  public void ejectQuarter(){
    this.state.ejectQuarter();
  }

  public void turnCrank(){
    this.state.turnCrank();
    this.state.dispense();
  }

  public State getNoQuarterState(){
    return this.noQuarterState;
  }

  public State getHasQuarterState(){
    return this.hasQuarterState;
  }

  public State getSoldState(){
    return this.soldState;
  }

  public State getSoldOutState(){
    return this.soldOutState;
  }

  public State getWinnerState(){
    return this.winnerState;
  }


  public int getGumballCount(){
    return this.gumballCount;
  }

  public void setGumballCount(int gumballCount){
    this.gumballCount = gumballCount;
  }

  public void setState(State state){
    this.state = state;
  }

  public void releaseGumball(){
    if (this.gumballCount > 0){
      System.out.println("gumball comes rolling out the slot");
      this.gumballCount--;
    }
  }
}


class WinnerState implements State {
  private GumballMachine gumballMachine;

  public WinnerState(GumballMachine gumballMachine){
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter(){
    System.out.println("rejecting quarter insert - please wait, we're already giving you a gumball");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("rejecting quarter ejection request - no quarter in slot anymore, you already turned the crank");
  }

  @Override
  public void turnCrank() {
    System.out.println("you already turned the crank");
  }

  @Override
  public void dispense() {
    this.gumballMachine.releaseGumball();
    if (this.gumballMachine.getGumballCount() == 0){
      this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
    } else {
      this.gumballMachine.releaseGumball();
      System.out.println("Your're a winner! you got two gumballs for your quarter");
      if (this.gumballMachine.getGumballCount() == 0){
        this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
      } else {
        this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
      }
    }
  }
}


class SoldOutState implements State {
  private GumballMachine gumballMachine;
  public SoldOutState(GumballMachine gumballMachine){
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter(){
    System.out.println("gumball machine sold out");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("no quarter has been inserted - can't eject");
  }

  @Override
  public void turnCrank() {
    System.out.println("no quarter has been inserted, can't turn crank for gumball without quarter");
  }

  @Override
  public void dispense() {
    System.out.println("no quarter has been inserted - can't dispense without paying first");
  }
}

class SoldState implements State {
  private GumballMachine gumballMachine;
  public SoldState(GumballMachine gumballMachine){
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter(){
    System.out.println("rejecting quarter insert - please wait, we're already giving you a gumball");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("rejecting quarter ejection request - no quarter in slot anymore, you already turned the crank");
  }

  @Override
  public void turnCrank() {
    System.out.println("you already turned the crank");
  }

  @Override
  public void dispense() {
    this.gumballMachine.releaseGumball();
    if (this.gumballMachine.getGumballCount() > 0){
      this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
    } else {
      this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
    }
  }
}


class HasQuarterState implements State {
  private GumballMachine gumballMachine;
  public HasQuarterState(GumballMachine gumballMachine){
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter(){
    System.out.println("can't insert quarter - quarter already inserted");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("ejecting quarter");
    this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
  }

  @Override
  public void turnCrank() {
    System.out.println("turning crank");
    this.gumballMachine.transactionsCount++;
    if (Math.floorMod(this.gumballMachine.transactionsCount, 2) == 0){
      this.gumballMachine.setState(this.gumballMachine.getWinnerState());
    } else {
      this.gumballMachine.setState(this.gumballMachine.getSoldState());
    }
  }

  @Override
  public void dispense() {
    System.out.println("no gumball dispensed. quarter still in slot. turn the crank first");
  }
}


class NoQuarterState implements State {
  private GumballMachine gumballMachine;
  public NoQuarterState(GumballMachine gumballMachine){
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter(){
    System.out.println("quarter has been inserted");
    this.gumballMachine.setState(this.gumballMachine.getHasQuarterState());
  }

  @Override
  public void ejectQuarter() {
    System.out.println("no quarter has been inserted - can't eject");
  }

  @Override
  public void turnCrank() {
    System.out.println("no quarter has been inserted, can't turn crank for gumball without quarter");
  }

  @Override
  public void dispense() {
    System.out.println("no quarter has been inserted - can't dispense without paying first");
  }
}

interface State {
  void insertQuarter();
  void ejectQuarter();
  void turnCrank();
  void dispense();
}
