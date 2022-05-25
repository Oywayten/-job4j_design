package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(s -> s.split("=", 2))
                .peek(strings -> {
                    if (strings[1].isEmpty()) {
                        throw new IllegalArgumentException("Введите верное значение");
                    }
                    strings[0] = strings[0].split("-", 2)[1];
                }).forEach(s -> values.putIfAbsent(s[0], s[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}