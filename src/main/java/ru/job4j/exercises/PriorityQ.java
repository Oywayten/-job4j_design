package ru.job4j.exercises;

public class PriorityQ {
    private int size;
    private int[] arr;
    private int count;

    public PriorityQ(int s) {
        size = s;
        arr = new int[size];
        count = 0;
    }

    public void insert(int i) {
        if (count == 0) {
            arr[count++] = i;
        } else {
            int j;
            for (j = 0; j <= count - 1; j++) {
                if (i > arr[count - 1 - j]) {
                    arr[count - j] = arr[count - 1 - j];
                } else {
                    break;
                }
            }
            arr[count - j] = i;
            count++;
        }
    }

    public int remove() {
        return arr[--count];
    }

    public int peek() {
        return arr[count];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }
}

class PriorityApp {
    public static void main(String[] args) {
        PriorityQ q = new PriorityQ(5);
        q.insert(30);
        q.insert(50);
        q.insert(40);
        q.insert(10);
        q.insert(20);
        while (!q.isEmpty()) {
            System.out.println(q.remove());
        }
    }
}
