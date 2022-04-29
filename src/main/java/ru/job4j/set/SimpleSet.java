package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean b = !contains(value);
        if (b) {
            set.add(value);
        }
        return b;
    }

    @Override
    public boolean contains(T value) {
        boolean b = false;
        for (T t : set) {
            if (t == value) {
                b = true;
                break;
            }
        }
        return b;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}