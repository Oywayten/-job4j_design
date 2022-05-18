package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResultFile {
    public class Matrix {
        public static int[][] multiple(int size) {
            int[][] array = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    array[i][j] = (i + 1) * (j + 1);
                }
            }
            return array;
        }
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] ints : Matrix.multiple(5)) {
                out.write(Arrays.toString(ints).getBytes(StandardCharsets.UTF_8));
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
