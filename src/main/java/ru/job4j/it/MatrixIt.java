package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length - 1 && data[row].length == 0) {
            row++;
            column = 0;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        int rsl;
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (row == data.length - 1 && column < data[row].length) {
            rsl = data[row][column++];
        } else {
            rsl = data[row++][0];
        }
        return rsl;
    }
}
