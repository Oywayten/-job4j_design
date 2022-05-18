package ru.job4j.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

class StackX {
    int arrSize;
    char[] stackArr;
    int top;

    public StackX(int size) {
        arrSize = size;
        stackArr = new char[arrSize];
        top = -1;
    }

    public void push(char i) {
        stackArr[++top] = i;
    }

    public int pop() {
        return stackArr[top--];
    }

    public int peep() {
        return stackArr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arrSize - 1;
    }
}

class Reverser {
    private String input;
    private String output;

    public Reverser(String in) {
        input = in;
    }

    public String doRev() {
        StackX stackX = new StackX(input.length());
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            stackX.push(ch);
        }
        output = "";
        while (!stackX.isEmpty()) {
            output = "%s%s".formatted(output, (char) stackX.pop());
        }
        return output;
    }
}

class ReversApp {
    public static void main(String[] args) throws IOException {
        String input;
        String output;
        while (true) {
            System.out.println("Enter a string");
            System.out.flush();
            input = getString();
            if (Objects.equals(input, "")) {
                break;
            }
            Reverser reverser = new Reverser(input);
            output = reverser.doRev();
            System.out.println(output);
            System.out.println();
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}