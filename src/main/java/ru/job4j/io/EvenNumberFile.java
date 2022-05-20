package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder str = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                str.append((char) read);
            }
            String[] strings = str.toString().split(System.lineSeparator());
            for (String st : strings) {
                boolean rsl = Integer.parseInt(st) % 2 == 0;
                System.out.println(st + " is " + rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
