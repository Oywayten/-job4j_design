package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScannerExample2 {
    public static void main(String[] args) {
        String data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        Scanner sc = new Scanner(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8))).useDelimiter(", ");
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
