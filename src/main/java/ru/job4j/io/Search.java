package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".\\");
        System.out.println(Files.isDirectory(start));
        search(start, p -> p.toFile().getName().endsWith(".js")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> list = new LinkedList<>();

    public SearchFiles(Predicate<Path> cond) {
        condition = cond;
    }

    public List<Path> getPaths() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            list.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
