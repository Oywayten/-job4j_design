package ru.job4j.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UsageSet {
    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        for (String s : strings) {
            System.out.println("Текущий элемент: " + s);
        }
        System.out.println();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            System.out.println("Следующий элемент: " + it.next());
        }
        strings.remove("two");
        for (String s : strings) {
            System.out.println(s);
        }
        strings.add("two");
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("retainAll");
        strings.retainAll(Set.of("one", "two"));
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println(strings.addAll(List.of("one", "two", "five")));
        System.out.println("new set");
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("predicate");
        strings.removeIf(s -> s.contains("f"));
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("\"Stream\"");
        strings.stream().filter(s -> s.length() < 5).forEach(System.out::println);
    }
}