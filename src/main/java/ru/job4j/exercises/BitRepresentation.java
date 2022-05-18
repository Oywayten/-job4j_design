package ru.job4j.exercises;

import java.util.HashMap;

public class BitRepresentation {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
//            System.out.println("Loop number " + i);
//            System.out.println("After for loop = " + sb);
            sb.append(num % 2 == 0 ? 0 : 1);
//            System.out.println("After num % 2 = " + sb);
            sb.append((i + 1) % 8 == 0 ? " " : "");
//            System.out.println("After i + 1 = " + sb);
            num /= 2;
//            System.out.println("num = " + num);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        System.out.println(binary(126));
        int h = 0b0000111_01011011_11001101_00010101;
        System.out.println(h);
        int n = h >>> 16;
        System.out.println(binary(n));
        h = h ^ n;
        System.out.println(binary(h));
//        h = 123456789;
        System.out.println(Integer.toBinaryString(h));
        String str = String.format("%8s", Integer.toBinaryString(h).replaceAll(" ", "0"));
        System.out.println(str);
        h = 0b100010101;
        n = 0b110110000;
        System.out.println("h = " + h + "; n = " + n + ";");
//        System.out.println(binary(h & n));
        System.out.println(Integer.toBinaryString(h & n));
    }
}
