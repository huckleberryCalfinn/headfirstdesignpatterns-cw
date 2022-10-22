package com.huckleberrycalfinn.iterable;

import java.util.ArrayList;
import java.util.List;

public class IterablePeopleApp {
  public static void main(String[] args){
	People people = new People();
	people.add(new Person("jed", 50));
	people.add(new Person("toby", 40));
	people.forEach(new PersonPrinter());
	people.forEach(person -> System.out.println(person));
  }
}


class People implements MyIterable<Person>{
  public List<Person> personList;

  public People(){
	this.personList = new ArrayList<>();
  }

  public void add(Person p){
	this.personList.add(p);
  }

  public MyIterator<Person> iterator(){
	return new PeopleIterator(this);
  }

  public void forEach(MyConsumer<? super Person> action){
	MyIterator<Person> it = this.iterator();
	while (it.hasNext()){
	  action.accept(it.next());
	}
  }
}

class PeopleIterator implements MyIterator<Person> {
  private People people;
  private int position = 0;

  public PeopleIterator(People people) {
  	this.people = people;
  }

  public boolean hasNext(){
	if (this.position < this.people.personList.size()){
	  return true;
	} else {
	  return false;
	}
  }

  public Person next(){
	Person nextPerson = this.people.personList.get(this.position);
  	this.position++;
	return nextPerson;
  }
}


class Person {
  public String name;
  public int age;

  public Person(String name, int age){
	this.name = name;
	this.age = age;
  }

  @Override
  public String toString(){
	return "Person(name=" + this.name + ", age=" + this.age + ")";
  }
}

class PersonPrinter implements MyConsumer<Person> {
  public void accept(Person p){
	System.out.println(p);
  }
}


interface MyConsumer<T> {
  void accept(T t);
}

interface MyIterable<T> {
  MyIterator<T> iterator();
  void forEach(MyConsumer<?  super T> action);
}

interface MyIterator<T> {
  boolean hasNext();
  T next();
}
