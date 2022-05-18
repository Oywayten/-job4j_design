package ru.job4j.exercises;

import java.util.Arrays;

public class Queue3 {
    private int size;
    private int[] arr;
    private int rear;
    private int front;

    public Queue3(int s) {
        size = s + 1;
        arr = new int[size];
        rear = -1;
        front = 0;
    }

    public void insert(int i) {
        if (rear == size - 1) {
            rear = -1;
        }
        arr[++rear] = i;
    }

    public int remove() {
        int tmp = arr[front++];
        if (front == size) {
            front = 0;
        }
        return tmp;
    }

    public int peek() {
        return arr[front];
    }

    public boolean isEmpty() {
        return rear + 1 == front || front + size - 1 == rear;
    }

    public boolean isFull() {
        return rear + 2 == front || front + size - 2 == rear;
    }

    public int getSize() {
        if (rear >= front) {
            return rear - front + 1;
        } else {
            return size - front + rear + 1;
        }
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }
}

class QueueApp3 {
    public static void main(String[] args) {
        Queue3 queue3 = new Queue3(9);
        queue3.insert(10);
        queue3.insert(20);
        queue3.insert(30);
        queue3.insert(40);
        queue3.insert(50);
        queue3.insert(60);
        queue3.insert(70);
        queue3.insert(80);
        queue3.insert(90);
        queue3.insert(100);
        System.out.println(queue3.isEmpty());
        System.out.println();
        System.out.println(queue3.getFront());
        System.out.println(queue3.getRear());
    }
}