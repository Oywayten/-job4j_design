package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        try {
            return out.pop();
        } catch (NoSuchElementException e) {
            try {
                while (true) {
                    out.push(in.pop());
                }
            } catch (NoSuchElementException e1) {
                return out.pop();
            }
        }
    }

    public void push(T value) {
        in.push(value);
    }
}