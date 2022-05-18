package ru.job4j.exercises;

public class Queue2 {
    private int size;
    private int[] arr;
    private int rear;
    private int front;
    private int count;

    public Queue2(int s) {
        size = s;
        arr = new int[size];
        rear = -1;
        front = 0;
        count = 0;
    }

    public void insert(int i) {
        if (rear == size - 1) {
            rear = -1;
        }
        if (!isFull()) {
            count++;
            arr[++rear] = i;
        }
    }

    public int remove() {
        int tmp = arr[front++];
        if (front == size) {
            front = 0;
        }
        count--;
        return tmp;
    }

    public int peekFront() {
        return arr[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public int getCount() {
        return count;
    }
}

class QueueApp2 {
    public static void main(String[] args) {
        Queue2 queue2 = new Queue2(5);
        queue2.insert(10);
        queue2.insert(20);
        queue2.insert(30);
        queue2.insert(40);
        System.out.println("remove " + queue2.remove());
        System.out.println("remove " + queue2.remove());
        System.out.println("remove " + queue2.remove());
        System.out.println("remove " + queue2.remove());
        System.out.println("remove " + queue2.remove());
        System.out.println("remove " + queue2.remove());
        System.out.println(queue2.peekFront());
        queue2.insert(50);
        queue2.insert(60);
        queue2.insert(70);
        queue2.insert(80);
        queue2.insert(90);
        while (!queue2.isEmpty()) {
            System.out.print(queue2.remove() + " ");
        }
        System.out.println();
    }
}