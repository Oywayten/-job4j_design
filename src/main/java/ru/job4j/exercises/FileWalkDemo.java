package ru.job4j.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWalkDemo {
    public static void main(String[] args) {
        Path source = Paths.get("src/main/java/ru/job4j/exercises/serialization");
        Path target = Paths.get("src/main/java/ru/job4j/exercises/temp");
        try {
            Files.find(source, 3, (path, attributes) -> path.endsWith(".txt")).forEach(p -> {
                try {
                    Path q = target.resolve(source.relativize(p));
                    if (Files.isDirectory(p) && !Files.exists(p)) {
                        Files.createDirectory(q);
                    } else {
                        if (!Files.exists(q)) {
                            Files.copy(p, q);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
