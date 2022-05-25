package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not exist");
        }
        return values.get(key);
    }

    private void checkString(String s) throws IllegalArgumentException {
        if (!Pattern.matches("[-]\\S+[=](\\S+[ ]\\S+|\\S+)", s)) {
            throw new IllegalArgumentException("Введите верное значение");
        }
    }

    private void parse(String[] args) throws IllegalArgumentException {
        Arrays.stream(args)
                .peek(this::checkString)
                .map(s -> s.split("=", 2))
                .peek(strings -> strings[0] = strings[0].split("-", 2)[1])
                .forEach(s -> values.putIfAbsent(s[0], s[1]));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Введите корректные аргументы");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512 gdfgv", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}