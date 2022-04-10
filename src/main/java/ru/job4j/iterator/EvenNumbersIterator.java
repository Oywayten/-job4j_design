package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    private IntStream newStream() {
        return Arrays.stream(data).skip(index);
    }

    @Override
    public boolean hasNext() {
        return newStream().anyMatch(value -> value % 2 == 0);
    }

    @Override
    public Integer next() {
        return newStream().filter(value -> {
            index++;
            return value % 2 == 0;
        }).findFirst().getAsInt();
    }
}