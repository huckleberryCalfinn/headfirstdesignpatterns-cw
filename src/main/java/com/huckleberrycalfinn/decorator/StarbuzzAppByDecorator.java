package com.huckleberrycalfinn.decorator;

public class StarbuzzAppByDecorator {

  public static void main(String[] args){
	Beverage bevA = new Cream(
			new Mocha(
					new HouseBlend()
			)
	);
	bevA.setSize(Beverage.Size.VENTI);
	System.out.println(bevA.getDescription());
	System.out.println(bevA.getSize());
	System.out.println(bevA.cost());
  }

}


class Cream extends CondimentDecorator {
  public Cream(Beverage beverage){
	this.beverage = beverage;
  }
  public String getDescription(){
	return this.beverage.getDescription() + ", Cream";
  }
  public double cost(){
	double cost = this.beverage.cost();
	if (this.getSize() == Size.TALL){
	  return cost + 0.05;
	} else if ( this.getSize() == Size.GRANDE){
	  return cost + 0.10;
	} else if (this.getSize() == Size.VENTI){
	  return cost + 0.15;
	}
	return cost;
  }
}


class Mocha extends CondimentDecorator {
  public Mocha(Beverage beverage){
	this.beverage = beverage;
  }

  public String getDescription(){
	String beverageDescription = this.beverage.getDescription();
	return beverageDescription + ", Mocha";
  }

  public double cost(){
	if (this.getSize() == Size.TALL){
	  return this.beverage.cost() + 0.10;
	} else if (this.getSize() == Size.GRANDE){
	  return this.beverage.cost() + 0.15;
    } else if (this.getSize() == Size.VENTI) {
      return this.beverage.cost() + .20;
  	}
	return this.beverage.cost();
  }
}


abstract class CondimentDecorator extends Beverage {
  Beverage beverage;

  @Override
  public abstract String getDescription();

  @Override
  public Size getSize(){
	return this.beverage.getSize();
  }

  @Override public void setSize(Size size){
	this.beverage.setSize(size);
  }
}

class HouseBlend extends Beverage {
  public HouseBlend(){
	this.description = "House Blend Coffee";
  }

  public double cost(){
	return 0.89;
  }
}

class Espresso extends Beverage {
  public Espresso(){
	this.description = "Espresso";
  }

  public double cost(){
	return 1.99;
  }
}


abstract class Beverage {
  public enum Size {TALL, GRANDE, VENTI};
  Size size = Size.TALL;
  String description = "Unknown Beverage";

  public void setSize(Beverage.Size size){
	this.size = size;
  }

  public Beverage.Size getSize(){
	return this.size;
  }

  public String getDescription(){
	return this.description;
  };

  public abstract double cost();
}
