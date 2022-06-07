package ru.job4j.exercises;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;

public class DirectoryStreamDemo {
    public static void main(String[] args) {
        String pattern = "\\S*\\S*.\\S*";
        String dirName = "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\exercises";
        DirectoryStream.Filter<Path> how = entry -> Pattern.matches(pattern, entry.getFileName().toString());
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(dirName), how)) {
            System.out.println("Directory " + dirName);
            for (Path elem : dirStream) {
                BasicFileAttributes attributes = Files.readAttributes(elem, BasicFileAttributes.class);
                if (attributes.isDirectory()) {
                    System.out.print(" <DIR> ");
                } else {
                    System.out.print("       ");
                }
                System.out.println(elem.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
