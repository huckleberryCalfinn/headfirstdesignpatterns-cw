package com.huckleberrycalfinn.factorypattern;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class FactoryPatternApp {
  public static void main(String[] args){
    PizzaFactory pizzaFactory = new PizzaFactory();
    PizzaStore pizzaStore = new PizzaStore(pizzaFactory);
    Pizza cheesePizza = pizzaStore.orderPizza("cheese");
    Pizza meatloversPizza = pizzaStore.orderPizza("meatlovers");
    Pizza pepperoniPizza = pizzaStore.orderPizza("pepperoni");
    for (Pizza p : List.of(cheesePizza, meatloversPizza, pepperoniPizza)){
      System.out.println(p);
    }
  }
}

enum PizzaTopping {
  CHEESE,
  PEPPERONI,
  SAUSAGE,
  OLIVES,
}

interface Pizza {
  void prepare();
}

class CheesePizza implements Pizza {
  private List<PizzaTopping> toppings;

  public CheesePizza(){
    this.toppings = new ArrayList<>();
  }

  public void prepare(){
    this.toppings.add(PizzaTopping.CHEESE);
  }

}

class MeatLoversPizza implements Pizza {
  private List<PizzaTopping> toppings;

  public MeatLoversPizza(){
    this.toppings = new ArrayList<>();
  }

  public void prepare(){
    this.toppings.add(PizzaTopping.CHEESE);
    this.toppings.add(PizzaTopping.PEPPERONI);
    this.toppings.add(PizzaTopping.SAUSAGE);
  }
}

class PepperoniPizza implements Pizza {
  private List<PizzaTopping> toppings;

  public PepperoniPizza(){
    this.toppings = new ArrayList<>();
  }

  public void prepare(){
    this.toppings.add(PizzaTopping.CHEESE);
    this.toppings.add(PizzaTopping.PEPPERONI);
  }
}

class PizzaFactory {
  public PizzaFactory(){}
  public Pizza createPizza(String type){
    Pizza pizza = null;
    if (type == "cheese") {
      pizza = new CheesePizza();
    } else if (type == "meatlovers"){
      pizza = new MeatLoversPizza();
    } else if (type == "pepperoni"){
      pizza = new PepperoniPizza();
    }
    return pizza;
  }
}

class PizzaStore {
  PizzaFactory factory;

  public PizzaStore(PizzaFactory factory){
    this.factory = factory;
  }

  public Pizza orderPizza(String type){
    Pizza pizza = this.factory.createPizza(type);
    pizza.prepare();
    return pizza;
  }
}
