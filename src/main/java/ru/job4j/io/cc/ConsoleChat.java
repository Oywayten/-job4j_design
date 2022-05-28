package ru.job4j.io.cc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> answers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        answers = readPhrases();
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/main/java/ru/job4j/io/cc/dialog.log", "src/main/java/ru/job4j/io/cc/answers.txt");
        System.out.println("Привет! Напиши мне что нибудь!");
        cc.run();
    }

    public void run() {
        Random random = new Random();
        String str;
        List<String> log = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
             PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            str = reader.readLine();
//            pw.println(str);
            log.add(str);
            while (!str.equalsIgnoreCase(OUT)) {
                log.add(str);
                switch (str) {
                    case STOP:
                        while (!CONTINUE.equalsIgnoreCase(str) && !OUT.equalsIgnoreCase(str)) {
                            str = reader.readLine();
                            log.add(str);
                            System.out.println(str);
                            System.out.println(str.equalsIgnoreCase(OUT));
                            if (str.equalsIgnoreCase(OUT)) {
                                break;
                            }
                        }
                    case OUT:
                        break;
                    default:
                        int i = random.nextInt(answers.size());
                        str = answers.get(i);
                        System.out.println(str);
                        pw.println(str);
                }
                str = reader.readLine();
                log.add(str);
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> list = null;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            list = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}