package com.scratch0.iterable;

import java.util.ArrayList;
import java.util.List;

public class IterablePeopleApp {
    public static void main(String[] args){
        People peeps  = new People(
            new Person("jed bartlett", 52),
            new Person("toby ziegler", 50),
            new Person("josh lymann", 35)
        );
        peeps.forEach(new PersonPrinter());
        peeps.forEach(person -> System.out.println(person));
    }
}

class PersonPrinter implements MyConsumer<Person> {
    public void accept(Person person){
        System.out.println(person);
    }
}

class People implements MyIterable<Person> {
    private List<Person> people;
    public People (Person... people){
        this.people = new ArrayList<Person>();
        this.people.addAll(List.of(people));
    }
    public MyIterator<Person> iterator(){
        return new PeopleIterator(this.people);
    }
    public void forEach(MyConsumer<? super Person> action){
        MyIterator<Person> it = this.iterator();
        while (it.hasNext()){
            action.accept(it.next());
        }
    }
}

class PeopleIterator implements MyIterator<Person> {
    private List<Person> people;
    private int pos;

    public PeopleIterator(List<Person> people){
        this.people = people;
        this.pos = 0;
    }
    public Person next(){
        Person person = this.people.get(this.pos);
        this.pos++;
        return person;
    }

    public boolean hasNext(){
        if (this.pos < this.people.size()){
            return true;
        }
        return false;
    }

}


class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        return "Person(name=" + this.name + ",age=" + this.age + ")";
    }
}

interface MyConsumer<T> {
    void accept(T obj);
}

interface MyIterable<T> {
    MyIterator<T> iterator();
    void forEach(MyConsumer<? super T> action);
}

interface MyIterator<T>{
    T next();
    boolean hasNext();
}