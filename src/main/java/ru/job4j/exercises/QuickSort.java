package ru.job4j.exercises;

import java.util.Arrays;

class QuickSort {
    public static int[] quickSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int pivot = arr[0];
        int[] less = new int[arr.length];
        int[] greater = new int[arr.length];
        int countLess = 0;
        int countGreater = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < pivot) {
                less[countLess++] = arr[i];
            } else if (arr[i] > pivot) {
                greater[countGreater++] = arr[i];
            }
        }
        less = Arrays.copyOf(less, countLess);
        greater = Arrays.copyOf(greater, countGreater);
        quickSort(less);
        quickSort(greater);
        if (less.length > 0) {
            System.arraycopy(less, 0, arr, 0, less.length);
        }
        arr[less.length] = pivot;
        if (greater.length > 0) {
            System.arraycopy(greater, 0, arr, less.length + 1, greater.length);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 36, 6, 2, 10};
        System.out.println(Arrays.toString(quickSort(arr)));
    }
}
