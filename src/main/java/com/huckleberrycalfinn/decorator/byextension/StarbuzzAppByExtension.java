package com.huckleberrycalfinn.decorator.byextension;

public class StarbuzzAppByExtension {
  public static void main(String[] args){
	Coffee coffee = new Coffee();
	coffee.setMilk(true);
	coffee.setSugar(true);

	Tea tea = new Tea();
	tea.setMilk(true);
	System.out.println(coffee.cost());
	System.out.println(tea.cost());
  }
}

class Tea  extends Beverage {
  @Override
  public double cost(){
	return super.cost() + 1.80;
  }
}

class Coffee extends Beverage {
  @Override
  public double cost(){
	double sumCost = 0;
	sumCost += super.cost();
	return sumCost + 3.00;
  }
}

class Beverage {
  private boolean milk;
  private boolean sugar;

  public void setMilk(boolean includesMilk){
	this.milk = includesMilk;
  }
  public boolean hasMilk(){
	return this.milk;
  }

  public void setSugar(boolean includesSugar){
	this.sugar = includesSugar;
  }

  public boolean hasSugar(){
	return this.sugar;
  }

  public double cost(){
	double sumCost = 0;
	if (this.hasMilk()){
	  sumCost += .20;
	}
    if (this.hasSugar()) {
	  sumCost += .10;
	}
	return sumCost;
  }
}
