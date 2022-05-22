package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> collect;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean b = false;
            collect = in.lines().collect(Collectors.toList());
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                String tmp = "";
                for (String s : collect) {
                    String tmp1 = s.substring(s.indexOf(' ') + 1) + ";";
                    if ((s.startsWith("400") || s.startsWith("500")) && !b) {
                        tmp = "";
                        b = true;
                        tmp += tmp1;
                    } else if ((s.startsWith("200") || s.startsWith("30000")) && b) {
                        b = false;
                        tmp += tmp1;
                        out.println(tmp);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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