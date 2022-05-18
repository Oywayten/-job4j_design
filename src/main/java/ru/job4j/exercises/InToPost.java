package ru.job4j.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

class StackX3 {
    private final char[] chars;
    private int top;

    public StackX3(int s) {
        chars = new char[s];
        top = -1;
    }

    public void push(char c) {
        chars[++top] = c;
    }

    public char pop() {
        return chars[top--];
    }

    public int peekN(int n) {
        return chars[n];
    }

    public int peek() {
        return chars[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getSize() {
        return top + 1;
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print(" Stack (bottom --> top): ");
        for (int i = 0; i < getSize(); i++) {
            System.out.print((char) peekN(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}

public class InToPost {
    String input;
    String output = "";
    StackX3 stackX3;

    public InToPost(@org.jetbrains.annotations.NotNull String s) {
        input = s;
        stackX3 = new StackX3(s.length());
    }

    public String doTrans(String in) {
        for (int i = 0; i < in.length(); i++) {
            char ch = in.charAt(i);
            stackX3.displayStack("For " + ch + " ");
            switch (ch) {
                case '+':
                case '-':
                    gotOper(ch, 1);
                    break;
                case '*':
                case '/':
                    gotOper(ch, 2);
                    break;
                case '(':
                    stackX3.push(ch);
                    break;
                case ')':
                    gotParen();
                    break;
                default:
                    output = "%s%s".formatted(output, ch);
                    break;
            }
        }
        while (!stackX3.isEmpty()) {
            stackX3.displayStack("While ");
            output = "%s%s".formatted(output, stackX3.pop());
        }
        stackX3.displayStack("End ");
        return output;
    }

    private void gotOper(char opThis, int opPriority) {
        while (!stackX3.isEmpty()) {
            char opTop = stackX3.pop();
            if (opTop == '(') {
                stackX3.push(opTop);
                break;
            } else {
                int topTriority;
                if (opTop == '+' || opTop == '-') {
                    topTriority = 1;
                } else {
                    topTriority = 2;
                }
                if (opPriority > topTriority) {
                    stackX3.push(opTop);
                    break;
                } else {
                    output = "%s%s".formatted(output, opTop);
                }
            }
        }
        stackX3.push(opThis);
    }

    public void gotParen() {
        while (!stackX3.isEmpty()) {
            char opTop = stackX3.pop();
            if (opTop == '(') {
                break;
            } else {
                output = "%s%s".formatted(output, opTop);
            }
        }
    }
}

class InfixApp {
    public static void main(String[] args) throws IOException {
        String input;
        String output;
        while (true) {

            System.out.println("Enter infix: ");
            System.out.flush();
            input = getString();
            if (Objects.equals(input, "")) {
                break;
            }
            InToPost post = new InToPost(input);
            output = post.doTrans(input);
            System.out.println(output);
        }
    }

    public static String getString() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        return bufferedReader.readLine();
    }
}
