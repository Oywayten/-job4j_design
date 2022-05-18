package ru.job4j.exercises;

import java.util.*;

class Graph {

    public static void main(String[] args) {
        Map<String, List<String>> friends = new HashMap<>(Map.of(
                "you", List.of("alice", "bob", "claire"),
                "bob", List.of("anuj", "peggy"),
                "alice", List.of("peggy"),
                "claire", List.of("thon", "jonny"),
                "anuj", List.of(),
                "peggy", List.of(),
                "thon", List.of("gnom"),
                "gnom", List.of(),
                "jonny", List.of()
        ));
        System.out.println(friends);
        System.out.println();
        System.out.println(search("you", friends));
    }

    private static boolean search(String you, Map<String, List<String>> friends) {

        ArrayDeque<String> frnds = new ArrayDeque<>(friends.get(you));
        Set<String> searched = new HashSet<>();
        while (frnds.size() > 0) {
            String person = frnds.pop();
            if (!searched.contains(person)) {
                if (person.contains("m")) {
                    System.out.println(person + " is a mango seller");
                    return true;
                } else {
                    frnds.addAll(friends.get(person));
                    searched.add(person);
                }
            }
        }
        return false;
    }
}