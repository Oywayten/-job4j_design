package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author Oywayten
 * @version 1.01 2022-06-09
 * Класс читает из файла параметры конфигурации, сохраняет ключи и значения параметров в словарь,
 * возвращает значение по ключу
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает все ключи в карту values.
     * Пропускает пустые строки и комментарии.
     */
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Map<String, String> tmp = br.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .map(s -> s.split("=", 2))
                    .filter(strings -> {
                        if (strings.length < 2 || strings[0].isEmpty() || strings[1].isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
            values.putAll(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает значение ключа key
     *
     * @param key ключ для поиска значения
     * @return значение для возврата
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}