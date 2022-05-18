package ru.job4j.exercises;

import java.util.Arrays;

public class SelectionSort {

    static void swap(int[] arr, int current, int compare) {
        if (current != compare) {
            int tmp = arr[current];
            arr[current] = arr[compare];
            arr[compare] = tmp;
        }
    }

    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int smaller = arr[i];
            int smallerIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < smaller) {
                    smaller = arr[j];
                    smallerIndex = j;
                }
            }
            swap(arr, i, smallerIndex);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        int[] arr = new int[]{5, 3, 6, 2, 10};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}