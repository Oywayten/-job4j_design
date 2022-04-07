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
        while /*(*/(data[row].length == 0 /*|| column == data[row].length - 1)*/ && row < data.length - 1) {
            row++;
            column = 0;
        }
        return /*row < data.length - 1 || !(row == data.length - 1 && */column < data[row].length/*)*/;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (column < data[row].length - 1) {
            return data[row][column++];
        } else if (row == data.length - 1 && column == data[row].length - 1) {
            return data[row][column++];
        } else {
            return data[row++][0];
        }
    }
}