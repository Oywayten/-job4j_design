package ru.job4j.exercises;

import java.util.Calendar;
import java.util.Formatter;

public class FormatDemo {
    public static void main(String[] args) {

        Formatter formatter = new Formatter();
        formatter.format("It's very easy to format with %s %d %f", "Java", 10, 98.86);
        System.out.println(formatter);
        Calendar calendar = Calendar.getInstance();
        formatter.close();
        formatter = new Formatter();
        formatter.format("%tc", calendar);
        System.out.println(formatter);
        formatter.close();
        formatter = new Formatter();
        formatter.format("%tb", calendar);
        System.out.println(formatter);
        calendar.set(Calendar.MONTH, 10);
        formatter.close();
        formatter = new Formatter();
        formatter.format("%tb", calendar);
        System.out.println(formatter);
        formatter.close();
    }
}
