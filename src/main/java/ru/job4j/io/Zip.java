package ru.job4j.io;

import ru.job4j.io.search.ArgsName;
import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Oywayten
 * @version 1.01 2022-06-09
 * Класс для архивации папки вместе с подпапками.
 * <pre>
 * Запуск программы:
 * {code
 * java -jar pack.jar -d=c:\project\job4j\ -e=.class -o=project.zip
 * }</pre>
 * java -jar pack.jar - Это собранный jar.
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы с расширением class.
 * -o - output - во что мы архивируем.
 */
public class Zip {
    ArrayList<String> list;

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.checkArgs(args);
        zip.packFiles(zip.searchFiles(new String[]{zip.list.get(0), zip.list.get(1)}),
                Paths.get(zip.list.get(2)).toFile());
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }

    /**
     * Метод ищет и исключает файлы, которые не стоит обрабатывать через метод Search.search(),
     * а потом возвращает список с подходящими файлами
     *
     * @param args параметры для работы метода: <p>args[0] - директория для поиска файлов;
     *             </p><p>args[1] - расширение файлов для исключения</p>
     * @return возвращает список файлов
     * @throws IOException исключение обхода каталогов
     */
    public List<File> searchFiles(String[] args) throws IOException {
        Search.checkArgs(args);
        return Search
                .search(Paths.get(args[0]), p -> !p.toFile().getName().endsWith(args[1]))
                .stream()
                .map(Path::toFile)
                .toList();
    }

    /**
     * Метод архивирует список файлов в целевой архив
     *
     * @param sources список файлов для архивации
     * @param target  целевой архив
     */
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод сохраняет переданные в массиве аргументы
     * в список list и проверяет,
     * что указанный путь является директорией
     *
     * @param args переданный массив аргументов
     */
    public void checkArgs(String[] args) {
        ArgsName pars = ArgsName.of(args);
        String directory = pars.get("d");
        String exclude = pars.get("e");
        String output = pars.get("o");
        list = new ArrayList<>(List.of(directory, exclude, output));
        if (!Paths.get(directory).toFile().isDirectory()) {
            throw new IllegalArgumentException("Enter a valid directory path");
        }
    }

    /**
     * Метод архивирует один файл
     *
     * @param source файл для архивации
     * @param target итоговый архив
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}