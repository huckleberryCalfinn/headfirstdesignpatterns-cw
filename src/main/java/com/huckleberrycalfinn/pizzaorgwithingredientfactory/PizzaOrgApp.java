package com.huckleberrycalfinn.pizzaorgwithingredientfactory;

import java.util.ArrayList;
import java.util.List;

public class PizzaOrgApp {
  public static void main(String[] args){
	PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
	PizzaStore newYorkPizzaStore = new NewYorkPizzaStore();
	for (PizzaType pizzaType : List.of(PizzaType.CHEESE, PizzaType.VEGGIE)){
	  newYorkPizzaStore.orderPizza(pizzaType);
	  System.out.println();
	  chicagoPizzaStore.orderPizza(pizzaType);
	  System.out.println();
	}
  }
}

//class ChicagoPizzaStore extends PizzaStore {
//  @Override
//  public Pizza createPizza(PizzaType pizzaType){
//	Pizza pizza = null;
//	if (pizzaType.equals(PizzaType.CHEESE)){
//	  pizza = new CheeseChicagoStylePizza();
//	} else if (pizzaType.equals(PizzaType.VEGGIE)){
//	  pizza = new VeggieChicagoStylePizza();
//	}
//	return pizza;
//  }
//}

class NewYorkPizzaStore extends PizzaStore {
  @Override
  public Pizza createPizza(PizzaType pizzaType){
	IngredientFactory ingredientFactory = new NewYorkIngredientFactory();
	Pizza pizza = null;
	if (pizzaType.equals(PizzaType.CHEESE)){
	  pizza = new CheesePizza(ingredientFactory);
	} else if (pizzaType.equals(PizzaType.VEGGIE)){
	  pizza = new VeggiePizza(ingredientFactory);
	}
	return pizza;
  }
}

class ChicagoPizzaStore extends PizzaStore {
  @Override
  public Pizza createPizza(PizzaType pizzaType){
	IngredientFactory ingredientFactory = new ChicagoIngredientFactory();
	Pizza pizza = null;
	if (pizzaType.equals(PizzaType.CHEESE)){
	  pizza = new CheesePizza(ingredientFactory);
	} else if (pizzaType.equals(PizzaType.VEGGIE)){
	  pizza = new VeggiePizza(ingredientFactory);
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



//class VeggieChicagoStylePizza extends ChicagoStylePizza {
//  public VeggieChicagoStylePizza(){
//	super();
//	this.name = "VEGGIE_" + this.name;
//	this.toppings.addAll(
//			List.of(PizzaTopping.CHEESE,
//					PizzaTopping.PEPPERS,
//					PizzaTopping.ONION,
//					PizzaTopping.BLACK_OLIVES));
//  }
//}
//
//class CheeseChicagoStylePizza extends ChicagoStylePizza {
//  public CheeseChicagoStylePizza(){
//	super();
//	this.name = "CHEESE_" + this.name;
//	this.toppings.add(PizzaTopping.CHEESE);
//  }
//}

//abstract class ChicagoStylePizza extends Pizza {
//
//  public ChicagoStylePizza(IngredientFactory)
//
//  @Override
//  public void bake(){
//	System.out.println("Baking for 30 minutes at 350 degrees fahrenheit.");
//  }
//}

class VeggiePizza extends Pizza {
  public VeggiePizza(IngredientFactory ingredientFactory){
	super(ingredientFactory);
	this.name = "VEGGIE_PIZZA";
	this.toppings.addAll(List.of(PizzaTopping.BLACK_OLIVES, PizzaTopping.ONION, PizzaTopping.PEPPERS));
  }
}

class CheesePizza extends Pizza {
  public CheesePizza(IngredientFactory ingredientFactory){
	super(ingredientFactory);
	this.name = "CHEESE_PIZZA";
  }
}

class NewYorkIngredientFactory implements IngredientFactory {
  public CRUST createCrust(){
	return CRUST.THIN;
  }
  public CHEESE createCheese(){
	return CHEESE.LITTLE;
  }
  public SAUCE createSauce(){
	return SAUCE.LIGHT;
  }
}


class ChicagoIngredientFactory implements IngredientFactory {
  public CRUST createCrust(){
	return CRUST.THICK;
  }
  public CHEESE createCheese(){
	return CHEESE.EXTRA;
  }
  public SAUCE createSauce(){
	return SAUCE.HEAVY;
  }
}

interface IngredientFactory {
  CRUST createCrust();
  CHEESE createCheese();
  SAUCE createSauce();
}

abstract class Pizza {
  IngredientFactory ingredientFactory;
  String name;
  CRUST crust;
  CHEESE cheese;
  SAUCE sauce;
  List<PizzaTopping> toppings;

  public Pizza(IngredientFactory ingredientFactory){
	this.ingredientFactory = ingredientFactory;
	this.crust = this.ingredientFactory.createCrust();
	this.cheese = this.ingredientFactory.createCheese();
	this.sauce = this.ingredientFactory.createSauce();
	this.toppings = new ArrayList<>();
  }

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
