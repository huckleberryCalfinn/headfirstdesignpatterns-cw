package com.huckleberrycalfinn.simplepizzafactory;

public class PizzaStoreApp {
	public static void main(String[] args){
		PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());
		System.out.println(
				pizzaStore.orderPizza("veggie").describe()
		);
	  System.out.println(pizzaStore.pizzaToString(pizzaStore.orderPizza("veggie"), new PizzaDescriber()));
	  System.out.println(pizzaStore.pizzaToString(pizzaStore.orderPizza("cheese"), new PizzaDescriber()));
	  System.out.println(pizzaStore.pizzaToString(pizzaStore.orderPizza("veggie"),
			  pizza -> "PIZZZZZA_____pizza" + pizza.describe()));
	}
}

class PizzaStore {
  private SimplePizzaFactory factory;

  public PizzaStore(SimplePizzaFactory factory){
	this.factory = factory;
  }

  public Pizza orderPizza(String type){
	return factory.makePizza(type);
  }

  public String pizzaToString(Pizza pizza, CalvinFunction<Pizza, String> pizzaFunc){
	return pizzaFunc.CalvinApply(pizza);
  }
}

class SimplePizzaFactory {
  public Pizza makePizza(String type){
	Pizza pizza = null;
	if (type == "cheese"){
	  pizza = new CheesePizza();
	} else if (type == "veggie"){
	  pizza = new VeggiePizza();
	}
	return pizza;
  }
}

class CheesePizza implements Pizza{
  public String describe(){
	return "CHEESE_PIZZA";
  }
}

class VeggiePizza implements Pizza {
  public String describe(){
	return "VEGGIE_PIZZA";
  }
}

interface Pizza {
  String describe();
}

class PizzaDescriber implements CalvinFunction<Pizza, String>{
  public String CalvinApply(Pizza pizza){
	return pizza.describe();
  }
}

interface CalvinFunction<T, R>{
  R CalvinApply(T o);
}
