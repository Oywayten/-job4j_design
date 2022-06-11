package ru.job4j.io;

import java.io.*;

/**
 * @author Oywayten
 * @version 1.01 2022-06-09
 * Класс для анализа доступности сервера
 */
public class Analizy {

    /**
     * Метод ищет диапазоны, когда не работал сервер
     *
     * @param source путь к файлу лога
     * @param target путь к файлу результата анализа
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean b = false;
            String tmp = "";
            String s = in.readLine();
            while (s != null) {
                String tmp1 = s.substring(s.indexOf(' ') + 1) + ";";
                if ((s.startsWith("400") || s.startsWith("500")) && !b) {
                    tmp = "";
                    b = true;
                    tmp += tmp1;
                } else if ((s.startsWith("200") || s.startsWith("300")) && b) {
                    b = false;
                    tmp += tmp1;
                    out.println(tmp);
                }
                s = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}