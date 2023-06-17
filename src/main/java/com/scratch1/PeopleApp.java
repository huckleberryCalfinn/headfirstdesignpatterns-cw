package com.scratch1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PeopleApp {
    public static void main(String[] args){
        People people = People.createDefaultPeople();
        people.forEach(new PersonPrinter());
    }
}


class PersonPrinter implements MyConsumer<Person> {
    public void accept(Person person){
        System.out.println(String.valueOf(person));
    }
}

class People implements MyIterable<Person> {
    private List<Person> personList;

    public People(){
        this.personList = new ArrayList<Person>();
    }

    public MyIterator<Person> iterator(){
        return new PersonIterator(this.personList);
    }

    public void forEach(MyConsumer<? super Person> action){
        MyIterator<Person> it = this.iterator();
        while (it.hasNext()){
            action.accept(it.next());
        }
    }

    public void addAll(Person... people){
        for (Person person : people){
            this.personList.add(person);
        }
    }

    public static People createDefaultPeople(){
        People people = new People();
        people.addAll(
            new Person("jed", 52),
            new Person("josh", 30),
            new Person("toby", 50)
        );
        return people;
    }
}


class PersonIterator implements MyIterator<Person> {
    private List<Person> people;
    private int pos;

    public PersonIterator(List<Person> people){
        this.people = people;
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
        return "Person(name="+this.name+",age=" + this.age + ")";
    }
}


interface MyConsumer<T> {
    void accept(T obj);
}

interface MyIterable<T> {
    MyIterator<T> iterator();
    void forEach(MyConsumer<? super T> action);
}

interface MyIterator<T> {
    T next();
    boolean hasNext();
}