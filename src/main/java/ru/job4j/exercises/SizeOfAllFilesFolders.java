package ru.job4j.exercises;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class SizeOfAllFilesFolders {
    public static void main(String[] args) {
        File folder = new File("./");
        Arrays.stream(folder.listFiles()).filter(File::isFile).forEach(s -> System.out.println(s.getName()));

        Path testFilePath = Paths.get("C:\\Users\\Username\\Desktop\\testFile.txt");

        System.out.println(testFilePath.endsWith("testFile.txt"));
        System.out.println(testFilePath.endsWith("Desktop/testFile.txt"));
    }
}
