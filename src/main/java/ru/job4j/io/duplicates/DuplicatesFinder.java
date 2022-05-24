package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor finder = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), finder);
        finder.getMap().entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .flatMap(entry -> entry.getValue().stream()).forEach(System.out::println);
    }
}