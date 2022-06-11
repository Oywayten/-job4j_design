package ru.job4j.io.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author Oywayten
 * @version 1.01
 * @since 2022-06-11
 * <p></p>
 * Класс ищет файлы по условию в заданном каталоге и подкаталогах.
 * Имя файла может задаваться, целиком, по маске, по регулярному выражению.
 * Результаты поиска записываются в файл.
 * Запуск программы: java -jar find.jar -d=c:/ -n=*.?xt -t=mask -o=log.txt
 * Ключи для запуска
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * Ключи валидируются.
 */
public class SearchByCriteria {
    ArrayList<String> list;

    public static void main(String[] args) {
        SearchByCriteria criteria = new SearchByCriteria();
        criteria.checkArgs(args);
        List<File> files = null;
        try {
            files = criteria.searchFiles(new String[]{criteria.list.get(0), criteria.list.get(1), criteria.list.get(2)});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(criteria.list.get(3)))) {
            Objects.requireNonNull(files).forEach(file -> {
                try {
                    bw.write(file.toString().concat(System.lineSeparator()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Predicate<Path> condition(String condition, String searchType) {
        Predicate<Path> pr;
        switch (searchType) {
            case "name":
                pr = p -> p.getFileName().toString().equals(condition);
                break;
            case "mask":
            default:
                pr = p -> Pattern.matches(condition, p.toFile().getName());
                break;
        }
        return pr;
    }

    /**
     * Метод сохраняет переданные в массиве аргументы
     * в список list и проверяет их корректность.
     *
     * @param args переданный массив аргументов
     */
    public void checkArgs(String[] args) {
        ArgsName pars = ArgsName.of(args);
        String directory = pars.get("d");
        String condition = pars.get("n");
        String searchType = pars.get("t");
        String output = pars.get("o");
        if (!Paths.get(directory).toFile().isDirectory()) {
            throw new IllegalArgumentException("Enter a valid directory path");
        } else if (!Paths.get(output).getParent().toFile().isDirectory()) {
            throw new IllegalArgumentException("Enter a valid output path");
        } else if (!Pattern.matches("mask|name|regex", searchType)) {
            throw new IllegalArgumentException("Enter a valid search type");
        }
        list = new ArrayList<>(List.of(directory, condition, searchType, output));
    }

    /**
     * Метод ищет файлы по условию,
     * а потом возвращает список с подходящими файлами
     *
     * @param args параметры для работы метода:
     *             * args[0] - директория, в которой начинать поиск.
     *             * args[1] - имя файла, маска, либо регулярное выражение.
     *             * args[2] - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
     * @return возвращает список файлов
     * @throws IOException исключение обхода каталогов
     */
    public List<File> searchFiles(String[] args) throws IOException {
        System.out.println("Args");
        return Search
//                .search(Paths.get(args[0]), p -> !p.toFile().getName().endsWith(args[1]))
                .search(Paths.get(args[0]), condition(args[1], args[2]))
                .stream()
                .map(Path::toFile)
                .toList();
    }
}
