package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Класс читает данные из CSV файла и выводит их. В качестве входных данных задается путь к файлу path,
 * разделитель delimiter, приемник данных out и фильтр по столбцам filter. Ключ -out имеет только два допустимых
 * значения: stdout или путь к файлу, куда нужно вывести. Например, если есть файл CSV со столбцами name, age,
 * birthDate, education, children и программа запускается таким образом:
 * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
 * то программа читает файл по пути file.csv и выводит только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 */
public class CSVReader {
    public static void checkArgs(String source, String delimiter, String target, String filter) {
        if (null == source || null == delimiter || null == target || null == filter) {
            throw new IllegalArgumentException("Set the right arguments");
        }
        if (!Files.exists(Paths.get(source)) || !Files.exists(Paths.get(target))) {
            throw new IllegalArgumentException(
                    "Invalid Path. Specify the correct path to the source and target folder");
        }
        if (!Pattern.matches("[,;\\t]", delimiter)) {
            throw new IllegalArgumentException("Please enter a valid delimiter");
        }
    }

    /**
     * Метод обрабатывает переданные параметры и формирует итоговый файл
     *
     * @param name экземпляр ArgsName со словарем параметров для работы метода
     */
    public static void handle(ArgsName name) {
        File source = new File(name.get("path"));
        String target = name.get("out");
        String delimiter = name.get("delimiter");
        String rsl = "";
        List<String> filter = List.of(name.get("filter").split(","));
        checkArgs(name.get("path"), delimiter, target, name.get("filter"));
        try (Scanner sc = new Scanner(source).useDelimiter(delimiter)) {
            while (sc.hasNext()) {
                List<String> line = List.of(sc.nextLine().split(delimiter));
                rsl = filterString(line, delimiter, filter, rsl);
            }
            output(target, rsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выводит результат в консоль или в файл в зависимости от параметра out.
     *
     * @param target целевой вывод
     * @param rsl    строка для вывода
     */
    private static void output(String target, String rsl) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(target))) {
            if ("stdout".equals(target)) {
                System.out.println(rsl);
            } else {
                pw.print(rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод формирует результирующую строку из исходной с учетом параметров фильтра
     *
     * @param line      строка для обработки в виде связного списка слов
     * @param delimiter разделитель
     * @param filter    фильтр со списком заголовков
     * @param rsl       результирующая строка
     * @return возвращает итоговую строку rsl
     */
    private static String filterString(List<String> line, String delimiter, List<String> filter, String rsl) {
        for (String elem : filter) {
            rsl = rsl.concat(line.get(filter.indexOf(elem))).concat(delimiter);
        }
        return rsl.substring(0, rsl.length() - 1).concat(System.lineSeparator());
    }
}
