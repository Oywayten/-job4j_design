package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> map = new HashMap<>();

    public Map<FileProperty, List<String>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            FileProperty property = new FileProperty(file.toFile().length(), file.toFile().getName());
            if (map.containsKey(property)) {
                map.get(property).add(file.toFile().getAbsolutePath());
            }
            map.putIfAbsent(property, new LinkedList<>(List.of(file.toFile().getAbsolutePath())));
        }
        return super.visitFile(file, attrs);
    }
}