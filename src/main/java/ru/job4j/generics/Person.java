package ru.job4j.generics;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Person {
    private String name;

    private int age;

    private Date birthday;

    public Person(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void printRsl(Collection<Object> col) {
        for (Object next : col) {
            System.out.println(next);
        }
    }

    /* getters and setters
       equals and hashcode
       toString */
}