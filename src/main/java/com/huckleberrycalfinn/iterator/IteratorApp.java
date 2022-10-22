package com.huckleberrycalfinn.iterator;

import java.util.ArrayList;
import java.util.List;

public class IteratorApp {
  public static void main(String[] args){
    PersonList personList = new PersonList();
    personList.add(new Person("jed", 52));
    personList.add(new Person("toby", 40));
    Iterator<Person> it = personList.createIterator();
      while (it.hasNext()){
        System.out.println(it.next());
      }
    }
  }


class PersonList implements Iterable<Person> {
  private List<Person> people;
  public PersonList(){
    this.people = new ArrayList<>();
  }
  public void add(Person person){
    this.people.add(person);
  }

  public Iterator<Person> createIterator(){
    return new PersonListIterator(this.people);
  }
}

class PersonListIterator implements Iterator<Person> {
  private List<Person> people;
  int position = 0;
  public PersonListIterator(List<Person> people){
    this.people = people;
  }
  public boolean hasNext(){
    if (position < this.people.size()){
      return true;
    } else {
      return false;
    }
  }
  public Person next(){
    Person nextPerson = this.people.get(position);
    this.position++;
    return nextPerson;
  }
}

interface Iterable<T> {
  Iterator<T> createIterator();
}

interface Iterator<T> {
  boolean hasNext();
  T next();
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
