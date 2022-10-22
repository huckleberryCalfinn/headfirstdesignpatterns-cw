package com.huckleberrycalfinn.regionalpizzafactory;

import java.util.ArrayList;
import java.util.List;

public class PizzaCorpApp {
  public static void main(String[] args){
	PizzaStore nyPizzaStore = new NYPizzaStore();
	PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
	for (PizzaType pizzaType : List.of(PizzaType.CHEESE, PizzaType.VEGGIE)){
	  nyPizzaStore.orderPizza(pizzaType);
	  System.out.println();
	  chicagoPizzaStore.orderPizza(pizzaType);
	  System.out.println();
	}
  }
}


class NYPizzaStore extends PizzaStore {
  public Pizza createPizza(PizzaType type){
	Pizza pizza = null;
	if (type == PizzaType.CHEESE){
	 pizza = new NYStyleCheesePizza();
	} else if (type == PizzaType.VEGGIE){
	  pizza = new NYStyleVeggiePizza();
	}
	return pizza;
  }
}

class ChicagoPizzaStore extends PizzaStore {
  @Override
  public Pizza createPizza(PizzaType pizzaType){
	Pizza pizza = null;
	if (pizzaType.equals(PizzaType.CHEESE)){
	  pizza = new CheeseChicagoStylePizza();
	} else if (pizzaType.equals(PizzaType.VEGGIE)){
	  pizza = new VeggieChicagoStylePizza();
	}
	return pizza;
  }
}

abstract class PizzaStore {

  public Pizza orderPizza(PizzaType type){
	Pizza pizza;
	pizza = this.createPizza(type);
	pizza.prepare();
	pizza.bake();
	pizza.cut();
	pizza.box();
	return pizza;
  }

  abstract Pizza createPizza(PizzaType pizza);
}

class VeggieChicagoStylePizza extends ChicagoStylePizza {
  public VeggieChicagoStylePizza(){
	super();
	this.name = "VEGGIE_" + this.name;
	this.toppings.addAll(
			List.of(PizzaTopping.CHEESE,
					PizzaTopping.PEPPERS,
					PizzaTopping.ONION,
					PizzaTopping.BLACK_OLIVES));
  }
}

class CheeseChicagoStylePizza extends ChicagoStylePizza {
  public CheeseChicagoStylePizza(){
	super();
	this.name = "CHEESE_" + this.name;
	this.toppings.add(PizzaTopping.CHEESE);
  }
}

abstract class ChicagoStylePizza extends Pizza {
  public ChicagoStylePizza(){
	this.toppings = new ArrayList<>();
	this.name = "CHICAGO_STYLE_PIZZA";
	this.crust = CRUST.THICK;
	this.sauce = SAUCE.HEAVY;
	this.cheese = CHEESE.EXTRA;
  }

  @Override
  public void bake(){
	System.out.println("Baking for 30 minutes at 350 degrees fahrenheit.");
  }
}

class NYStyleCheesePizza extends Pizza {
  public NYStyleCheesePizza() {
	this.toppings = new ArrayList<PizzaTopping>();
	this.name = "NY Style Cheese Pizza";
	this.crust = CRUST.THIN;
	this.cheese = CHEESE.LITTLE;
	this.sauce = SAUCE.LIGHT;
  }
}

class NYStyleVeggiePizza extends Pizza {
  public NYStyleVeggiePizza(){
	this.name = "NY_STYLE_VEGGIE_PIZZA";
	this.crust = CRUST.THIN;
	this.cheese = CHEESE.LITTLE;
	this.sauce = SAUCE.LIGHT;
	this.toppings = new ArrayList<PizzaTopping>();
  }
}

abstract class Pizza {
  String name;
  CRUST crust;
  CHEESE cheese;
  SAUCE sauce;
  List<PizzaTopping> toppings;

  public void prepare(){
	System.out.println("Preparing " + this.getName());
	System.out.println("Tossing dough: " + this.getCrust());
	System.out.println("Spreading sauce: " + this.getSauce());
	System.out.println("Adding cheese: " + this.getCheese());
	System.out.println("Adding toppings:");
	for (PizzaTopping topping : this.getToppings()){
	  System.out.println("\t" + topping);
	}
  }

  public void bake(){
	System.out.println("Baking for 25 minutes at 375");
  }

  public void cut(){
	System.out.println("Cutting the pizza into diagonal slices");
  }

  public void box(){
	System.out.println("Placing pizza in official PizzaStore box");
  }

  public CRUST getCrust(){
	return this.crust;
  }

  public CHEESE getCheese(){
	return this.cheese;
  }

  public SAUCE getSauce(){
	return this.sauce;
  }

  public String getName(){
	return this.name;
  }

  public List<PizzaTopping> getToppings(){
	List<PizzaTopping> returnToppings = new ArrayList<PizzaTopping>();
	for ( PizzaTopping topping : this.toppings ){
	  returnToppings.add(topping);
	}
	return returnToppings;
  }
}
enum CRUST {
  THIN,
  THICK
}

enum CHEESE {
  LITTLE,
  REGULAR,
  EXTRA
}

enum SAUCE {
  LIGHT,
  HEAVY
}

enum PizzaType {
  CHEESE,
  VEGGIE
}

enum PizzaTopping {
  CHEESE,
  PEPPERONI,
  SAUSAGE,
  PEPPERS,
  ONION,
  BLACK_OLIVES,
  PINEAPPLE,
  SARDINES
}


enum Ingredient{
  CRUST,
  CHEESE,
  SAUCE,
}
