package com.scratch5;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsAndCollectorsScratch {
    public static void main(String[] args){
        Person jed = new Person("jed bartlett", 50);
        Person abigail = new Person("abigail barltett", 52);
        Map<String, Person> parentNameToParentMap = List.of(jed, abigail).stream()
            .collect(Collectors.toMap(person -> person.getName(), Function.identity()));

        List<Person> people = List.of(
            new Person("josh lymann", 30, jed),
            new Person("toby ziegler", 40, jed),
            new Person("donna moss", 28, abigail),
            new Person("zoe bartlett", 22, abigail),
            new Person("cj craig", 40, jed)
        );
        System.out.println(parentNameToParentMap);
        Map<Person, List<Person>> guardianToWards = people.stream()
            .collect(Collectors.groupingBy(person -> parentNameToParentMap.get(person.getGuardian().getName())));
        System.out.println(guardianToWards);
        guardianToWards.forEach((person, personList) -> {
           System.out.println(
               String.format("Guardian: %s", person));
           for (Person child : personList){
               System.out.println(String.format("\t%s", child.toString()));
           }
        });
    }
}




class Person {
    private String name;
    private Integer age;
    private Person guardian;
    public Person(String name, Integer age){
        this(name, age, null);
    }
    public Person(String name, Integer age, Person guardian){
        this.name = name;
        this.age = age;
        this.guardian = guardian;    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Person getGuardian() {
        return guardian;
    }

    @Override
    public String toString(){
        String guardianString = "";
        if (this.guardian != null){
            guardianString = this.guardian.toString();
        }
        return "Person(name=" + this.name + ",age=" + this.age + "guardian=" + guardianString + ")";
    }
}