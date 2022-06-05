package ru.job4j.exercises;

import java.util.Formatter;

public class FormatDemo {
    public static void main(String[] args) {

        try (Formatter formatter = new Formatter()) {
            formatter.format("It's very easy to format with %s %d %f", "Java", 10, 98.86);
            System.out.println(formatter);
        }

    }
}
