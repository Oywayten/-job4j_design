package ru.job4j.assertj;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Oywayten on 01.12.2022.
 * Простая коллекция для теста работа AssertJ с итератором в коллекции.
 */
public class SimpleCollection<T> implements Iterable<T> {
    private final T[] container = (T[]) new Object[5];

    public SimpleCollection(T vo, T v1, T v2, T v3, T v4) {
        container[0] = vo;
        container[1] = v1;
        container[2] = v2;
        container[3] = v3;
        container[4] = v4;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < container.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("don't has next element");
                }
                return container[index++];
            }
        };
    }
}
