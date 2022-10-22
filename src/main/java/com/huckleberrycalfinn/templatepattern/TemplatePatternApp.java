package com.huckleberrycalfinn.templatepattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TemplatePatternApp {
  public static void main(String[] args){
    Coffee coffee = new Coffee();
    coffee.prepareBeverage();
  }
}


class Coffee extends CaffeineBeverage {
  public void brew(){
    System.out.println("brewing ground coffee beans");
  }
  public void addCondiments(){
    System.out.println("adding milk and creamer to coffee");
  }

  @Override
  public boolean customerWantsCondiments(){
    String answer = this.getUserInput("Would you like milk and creamer with your coffee (y/n)?");
    if (answer.toLowerCase().startsWith("y")){
      return true;
    } else  {
      return false;
    }
  }

  private String getUserInput(String prompt){
    String answer = null;
    System.out.println(prompt);
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try {
      answer = in.readLine();
    } catch (IOException ioe){
      System.err.println("IO error trying to read answer");
    }
    if (answer == null){
      return "n";
    }
    return answer;
  }
}

abstract class CaffeineBeverage {
  public void prepareBeverage(){
    this.boilWater();
    this.brew();
    this.pour();
    if (this.customerWantsCondiments()){
      this.addCondiments();
    }
  }

  public void boilWater(){
    System.out.println("boiling water");
  }

  public abstract void brew();

  public void pour(){
    System.out.println("pouring into cup");
  }

  public abstract void addCondiments();

  public boolean customerWantsCondiments(){
    return true;
  }
}
