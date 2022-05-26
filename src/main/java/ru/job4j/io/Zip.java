package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public List<File> searchFiles(String[] args) throws IOException {
        Search.checkArgs(args);
        return Search
                .search(Paths.get(args[0]), p -> !p.toFile().getName().endsWith(args[1]))
                .stream()
                .map(Path::toFile)
                .toList();
    }

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