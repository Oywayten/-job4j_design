package ru.job4j.generics;

import java.util.*;

public class GenericUsage {
    public void printRsl(Collection<?> col) {
        for (Object next : col) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printInfo(Collection<? super Programmer> col) {
        for (Object next : col) {
            System.out.println(next);
        }
    }

    public static void main(String[] args) {
        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);
    }
}