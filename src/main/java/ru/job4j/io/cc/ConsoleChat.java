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
        ConsoleChat cc = new ConsoleChat(
                "src/main/java/ru/job4j/io/cc/dialog.log", "src/main/java/ru/job4j/io/cc/answers.txt");
        cc.run();
    }

    public void run() {
        Random random = new Random();
        String str = null;
        List<String> log = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            str = reader.readLine();
            log.add(str);
            while (!OUT.equalsIgnoreCase(str)) {
                switch (str) {
                    case STOP:
                        str = reader.readLine();
                        log.add(str);
                        while (!CONTINUE.equalsIgnoreCase(str)) {
                            if (OUT.equalsIgnoreCase(str)) {
                                break;
                            }
                            str = reader.readLine();
                            log.add(str);
                        }
                        break;
                    case OUT:
                        break;
                    default:
                        int i = random.nextInt(answers.size());
                        str = answers.get(i);
                        log.add(str);
                        System.out.println(str);
                        str = reader.readLine();
                        log.add(str);
                        break;
                }
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