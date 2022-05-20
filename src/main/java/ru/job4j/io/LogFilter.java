package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> strings = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            strings = in.lines().filter(s -> s.contains(" 404 ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
    }
}
