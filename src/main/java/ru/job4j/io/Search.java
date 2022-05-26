package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс обходит дерево каталогов указанной категории и ищет файлы с указанным расширением.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static void checkArgs(String[] args) {
        if (args.length != 2) {
            System.out.println(args.length);
            Arrays.stream(args).forEach(System.out::println);
            throw new IllegalArgumentException("Invalid number of arguments. Use the path and file extension");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            System.out.println(args[0]);
            throw new IllegalArgumentException("Invalid Path. Specify the correct path to the search folder");
        }
        if (!args[1].startsWith(".")) {
            System.out.println(args[1]);
            throw new IllegalArgumentException("Check the second parameter - it must start with the character \".\"");
        }
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
