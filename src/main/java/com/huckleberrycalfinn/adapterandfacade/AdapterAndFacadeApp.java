package com.huckleberrycalfinn.adapterandfacade;

public class AdapterAndFacadeApp {
	public static void main(String[] args){
		Duck mallard = new MallardDuck();
		Turkey wildTurkey = new WildTurkey();
		Duck wildTurkeyDuck = new TurkeyAdapter(wildTurkey);
		mallard.quack();
		wildTurkey.gobble();
		wildTurkeyDuck.quack();
	}
}


class TurkeyAdapter implements Duck {
  private Turkey turkey;
  public TurkeyAdapter(Turkey turkey){
	this.turkey = turkey;
  }

  public void quack(){
	this.turkey.gobble();
  }

  public void fly(){
	this.turkey.fly();
  }
}

class WildTurkey implements Turkey {
  public void fly(){
	System.out.println("WILD_TURKEY_FLYING");
  }
  public void gobble(){
	System.out.println("WILD_TURKEY_GOBBLING");
  }
}

class MallardDuck implements Duck {
  public void quack(){
	System.out.println("MALLARD_DUCKS_QUACKING");
  }
  public void fly(){
	System.out.println("MALLARD_DUCK_FLYING");
  }
}

interface Duck {
  void quack();
  void fly();
}

interface Turkey {
  void fly();
  void gobble();
}
