package ru.job4j.io.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Принимает массив параметров и разбивает их на пары: ключ, значение
 */
public class ArgsName {
    /**
     * Словарь для хранения пар <ключ: значение>
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Фабричный метод создает объект ArgsName и заполняет словарь values на основе принятого массива аргументов
     *
     * @param args переданный массив аргументов
     * @return возвращает объект типа ArgsName
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Введите корректные аргументы");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    /**
     * Метод возвращает значение по ключу key
     *
     * @param key ключ для проверки присутствия значения
     * @return возвращает значение типа String
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not exist");
        }
        return values.get(key);
    }

    /**
     * Проверка строки на соответствие шаблону
     *
     * @param s входная строка, которую проверяем
     * @throws IllegalArgumentException если введено неверное значение
     */
    private void checkString(String s) throws IllegalArgumentException {
        if (!Pattern.matches("-\\S+=(\\S+ *\\S*)", s)) {
            throw new IllegalArgumentException("Введите верное значение");
        }
    }

    /**
     * Метод парсит переданный массив аргументов (тип String), проверяет их на соответствие шаблону и помещает пары
     * <ключ: значение> в Map<String, String> values
     *
     * @param args переданный массив аргументов
     * @throws IllegalArgumentException если аргументы не прошли валидацию
     */
    private void parse(String[] args) throws IllegalArgumentException {
        Arrays.stream(args)
                .peek(this::checkString)
                .map(s -> s.split("=", 2))
                .peek(strings -> strings[0] = strings[0].split("-", 2)[1])
                .forEach(s -> values.putIfAbsent(s[0], s[1]));
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512 gdfgv", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}