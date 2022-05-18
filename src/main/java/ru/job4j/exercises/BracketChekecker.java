package ru.job4j.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class StackX2 {
	private int size;
	private char[] arr;
	private int top;

	public StackX2(int size) {
		this.size = size;
		arr = new char[size];
		top = -1;
	}

	public void push(char ch) {
		arr[++top] = ch;
	}

	public char pop() {
		return arr[top--];
	}

	public char peep() {
		return arr[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == size - 1;
	}
}

public class BracketChekecker {
	private final String input;

	public BracketChekecker(String input) {
		this.input = input;
	}

	public void check() {
		StackX2 stackX2 = new StackX2(input.length());
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			switch (ch) {
				case '{':
				case '[':
				case '(':
					stackX2.push(ch);
					break;
				case '}':
				case ']':
				case ')':
					if (!stackX2.isEmpty()) {
						char chx = stackX2.pop();
						if (ch == '}' && chx != '{' || ch == ')' && chx != '(' || ch == ']' && chx != '[') {
							System.out.println("Error " + ch + " at " + i);
						}
					} else {
						System.out.println("Error else " + ch + " at " + i);
					}
					break;
				default:
					break;
			}
		}
		if (stackX2.isEmpty()) {
			System.out.println("All good");
		}
	}
}

class BacketApp {
	public static void main(String[] args) throws IOException {
		String input;
		while (true) {
			System.out.println("Enter string...");
			System.out.flush();
			input = getString();
			if (input.equals("")) {
				break;
			}
			BracketChekecker bracketChekecker = new BracketChekecker(input);
			bracketChekecker.check();
		}
	}

	private static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
}
