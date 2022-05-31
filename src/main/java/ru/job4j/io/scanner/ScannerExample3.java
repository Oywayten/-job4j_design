package ru.job4j.io.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScannerExample3 {
    public static void main(String[] args) {
        String data = "A 1B FF 110";
        File file = null;
        try {
            file = File.createTempFile("data", ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert file != null;
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                out.write(data.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Scanner scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
