package ru.job4j.exercises;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryStreamDemo {
    public static void main(String[] args) {
        String dirName = "/";
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(dirName))) {
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
