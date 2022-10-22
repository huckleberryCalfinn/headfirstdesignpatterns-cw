package com.huckleberrycalfinn.singleton;

public class SingletonApp {
  public static void main(String[] args){
    Boiler boiler = Boiler.getBoiler();
    System.out.println(boiler.getState());
    boiler.fill();
    System.out.println(boiler.getState());
    boiler.boil();
    System.out.println(boiler.getState());
    boiler.drain();
    System.out.println(boiler.getState());
  }
}

class Boiler {
  private boolean filled;
  private boolean boiled;
  private static Boiler uniqueInstance = new Boiler();

  private Boiler(){
    this.filled = false;
    this.boiled = false;
  }

  public static Boiler getBoiler(){
//    if (Boiler.uniqueInstance == null){
//      Boiler.uniqueInstance = new Boiler();
//    }
    return Boiler.uniqueInstance;
  }

  public void fill(){
    if (!this.isFilled()){
      // fill the boiler with milk and chocolate
      this.filled = true;
    }
  }

  public void boil(){
    if (this.isFilled() && !this.isBoiled()){
      // boil the chocolate and milk mixture in the boiler
      this.boiled = true;
    }
  }

  public void drain(){
    if (this.isFilled() && this.isBoiled()){
      // drain the boiler of it's contents if it's
      // full and boiled
      this.filled = false;
      this.boiled = false;
    }
  }

  public boolean isFilled(){
    return this.filled;
  }

  public boolean isBoiled(){
    return this.boiled;
  }

  public String getState(){
    return "Boiler(filled=" + this.isFilled() + ",boiled=" + this.isBoiled() + ")";
  }

}
