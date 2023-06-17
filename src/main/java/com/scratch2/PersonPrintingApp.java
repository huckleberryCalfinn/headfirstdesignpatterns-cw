package com.scratch2;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PersonPrintingApp {
    public static void main(String[] args){
        People people = People.createDefaultPeople();
        people.forEach(new PersonPrinter());
        System.out.println("yo");
        people.forEach(person -> System.out.println(String.valueOf(person).toUpperCase()));
    }
}


class People implements MyIterable<Person> {
    private List<Person> personList;
    public People(){
        this.personList = new ArrayList<Person>();
    }

    public void add(Person person){
        this.personList.add(person);
    }

    public void addAll(Person... people){
        this.personList.addAll(List.of(people));
    }

    public static People createDefaultPeople(){
        People people = new People();
        people.addAll(
            Person.builder().name("jed").age(52).build(),
            Person.builder().name("toby").age(50).build(),
            Person.builder().name("josh").age(35).build()
        );
        return people;
    }

    public MyIterator<Person> iterator(){
        return new PersonIterator(this.personList);
    }

    public void forEach(MyConsumer<? super Person> action){
        MyIterator<Person> personIterator = this.iterator();
        while (personIterator.hasNext()){
            action.accept(personIterator.next());
        }
    }

}

class PersonIterator implements MyIterator<Person> {
    private List<Person> people;
    private int pos;
    public PersonIterator(List<Person> people){
        this.people = new ArrayList<Person>();
        this.people.addAll(people);
    }

    public boolean hasNext(){
        return this.pos < this.people.size();
    }

    public Person next(){
        Person person = this.people.get(this.pos);
        this.pos++;
        return person;
    }
}


class PersonPrinter implements MyConsumer<Person> {
    public void accept(Person person){
        System.out.println(person);
    }
}


class Person {

    private final String name;
    private final int age;

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

    static PersonBuilder builder(){
        return new PersonBuilder();
    }

    static class PersonBuilder {
        String name;
        int age;

        public PersonBuilder(){
        }

        public PersonBuilder name(String name){
            this.name = name;
            return this;
        }

        public PersonBuilder age(int age){
            this.age = age;
            return this;
        }

        public Person build(){
            return new Person(this.name, this.age);
        }

    }
}

interface MyIterable<T> {
    MyIterator<T> iterator();
    void forEach(MyConsumer<? super T> action);
}

interface MyConsumer<T> {
    void accept(T obj);
}

interface MyIterator<T> {
    boolean hasNext();
    T next();
}
