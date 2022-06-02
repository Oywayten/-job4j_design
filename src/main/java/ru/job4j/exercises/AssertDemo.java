package ru.job4j.exercises;

public class AssertDemo {
    transient static int val = 3;

    static int getVal() {
        return val--;
    }

    public static void main(String[] args) {
        int n;
        for (int i = 0; i < 10; i++) {
            n = getVal();
            assert n > 0;
            System.out.println("n is " + n);
        }
    }
}
