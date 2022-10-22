package com.huckleberrycalfinn.pizzaabstractfactory0;

import java.util.ArrayList;
import java.util.List;

public class PizzaCorpApp {
  public static void main(String[] args){
    PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
    PizzaStore newYorkPizzaStore = new NewYorkPizzaStore();
    Pizza chicagoCheesePizza = chicagoPizzaStore.orderPizza(PizzaType.CHEESE);
    Pizza newYorkVeggiePizza = newYorkPizzaStore.orderPizza(PizzaType.VEGGIE);
  }
}

class NewYorkPizzaStore extends PizzaStore {
  public Pizza createPizza(PizzaType pizzaType){
    Pizza pizza = null;
    IngredientFactory ingredientFactory = new NewYorkIngredientFactory();
    if (pizzaType.equals(PizzaType.CHEESE)){
      pizza = new CheesePizza(ingredientFactory);
    } else if (pizzaType.equals(PizzaType.VEGGIE)){
      pizza = new VeggiePizza(ingredientFactory);
    }
    return pizza;
  }
}


class ChicagoPizzaStore extends PizzaStore {
  public Pizza createPizza(PizzaType pizzaType){
    Pizza pizza = null;
    if (pizzaType.equals(PizzaType.CHEESE)){
      pizza = new CheesePizza(new ChicagoIngredientFactory());
    } else if (pizzaType.equals(PizzaType.VEGGIE)){
      pizza = new VeggiePizza(new ChicagoIngredientFactory());
    }
    return pizza;
  }
}

abstract class PizzaStore {
  public Pizza orderPizza(PizzaType pizzaType){
    Pizza pizza = this.createPizza(pizzaType);
    pizza.prepare();
    pizza.bake();
    System.out.println(pizza.name + " is ready!");
    return pizza;
  }

  abstract Pizza createPizza(PizzaType pizzaType);
}

class VeggiePizza extends Pizza {
  public VeggiePizza(IngredientFactory ingredientFactory){
    super(ingredientFactory);
    this.name = PizzaType.VEGGIE.name();
    this.toppings.addAll(List.of(
            Topping.PEPPERS,
            Topping.BLACK_OLIVES,
            Topping.ONIONS
    ));
  }
}

class CheesePizza extends Pizza {
  public CheesePizza(IngredientFactory ingredientFactory){
    super(ingredientFactory);
    this.name = "CHEESE_PIZZA";
  }
}

abstract class Pizza {
  Crust crust;
  Cheese cheese;
  Sauce sauce;
  IngredientFactory ingredientFactory;
  List<Topping> toppings;
  String name;

  public Pizza(IngredientFactory ingredientFactory){
    this.ingredientFactory = ingredientFactory;
    this.crust = this.ingredientFactory.createCrust();
    this.cheese = this.ingredientFactory.createCheese();
    this.sauce = this.ingredientFactory.createSauce();
    this.toppings = new ArrayList<Topping>();
  }

  public void prepare(){
    System.out.println("Preparing " + this.name + "!");
    System.out.println("Tossing " + this.crust + " crust");
    System.out.println("Spreading " + this.sauce + " sauce.");
    System.out.println("Adding " + this.cheese + " cheese");
    for (Topping topping : this.toppings){
      System.out.println("Adding topping: " + String.valueOf(topping));
    }
  }

  public void bake(){
    System.out.println("Baking at 375 degrees Farhrenheit for 25 minutes.");
  }
}

class NewYorkIngredientFactory implements IngredientFactory {
  public Crust createCrust(){
    return Crust.THIN;
  }
  public Cheese createCheese(){
    return Cheese.LITTLE;
  }
  public Sauce createSauce(){
    return Sauce.LIGHT;
  }
}

class ChicagoIngredientFactory implements IngredientFactory {
  public Crust createCrust(){
    return Crust.THICK;
  }
  public Cheese createCheese(){
    return Cheese.EXTRA;
  }
  public Sauce createSauce(){
    return Sauce.HEAVY;
  }
}

interface IngredientFactory {
  Crust createCrust();
  Cheese createCheese();
  Sauce createSauce();
}

enum PizzaType {
  CHEESE,
  VEGGIE
}

enum Crust {
  THICK,
  THIN
}

enum Cheese {
  LITTLE,
  REGULAR,
  EXTRA
}

enum Sauce {
  LIGHT,
  MEDIUM,
  HEAVY
}

enum Topping {
  PEPPERS,
  BLACK_OLIVES,
  ONIONS;
}
