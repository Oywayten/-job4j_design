package ru.job4j.exercises;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class WalkFileTreeDemo {
    public static void main(String[] args) {
        String dirName = "./src/main/java/ru/job4j/";
        Path path = Paths.get(dirName);
        System.out.println("\"" + path.getFileName() + "\"" + " is root directory");
        try {
            Files.walkFileTree(path, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InetAddress addres = InetAddress.getByName("192.168.0.66");
            System.out.println(Arrays.toString(addres.getAddress()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println(" <DIR> " + dir.getFileName());
            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("       " + file.getFileName());
            return super.visitFile(file, attrs);
        }
    }
}
