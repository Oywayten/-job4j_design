package ru.job4j.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFilesDemo {
    public static void main(String[] args) {
        Path path = Paths.get("exercises", "home", "temp");
        Path parent = path.getParent();
        System.out.println(parent);
        Path file = path.getFileName();
        System.out.println(file);
        Path root = file.toAbsolutePath().getRoot();
        System.out.println(root);
        try {
            Path path1 = Paths.get("src/main/java/ru/job4j/exercises/");
            System.out.println(path1.toAbsolutePath());
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path file1 = Paths.get("Graph.java");
            Path source = path1.resolve(file1);
            Path target = path.resolve(file1);
            System.out.println(target);
            Files.copy(source, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
