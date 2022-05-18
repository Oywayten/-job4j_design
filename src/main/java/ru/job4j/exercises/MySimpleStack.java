package ru.job4j.exercises;

public class MySimpleStack {
    int arrSize;
    int[] arr;
    int top;

    public MySimpleStack(int size) {
        arrSize = size;
        arr = new int[arrSize];
        top = -1;
    }

    public void push(int num) {
        arr[++top] = num;
    }

    public int pop() {
        return arr[top--];
    }

    public int peek() {
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arrSize - 1;
    }
}

class StackApp {

    public static void main(String[] args) {
        MySimpleStack mySimpleStack = new MySimpleStack(10);
        mySimpleStack.push(15);
        mySimpleStack.push(2);
        mySimpleStack.push(132);
        while (!mySimpleStack.isEmpty()) {
            System.out.print(mySimpleStack.pop());
            System.out.print(" ");
        }
    }

}
