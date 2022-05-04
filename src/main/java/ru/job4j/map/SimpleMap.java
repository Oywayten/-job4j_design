package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int hsh = hash(key.hashCode());
        int index = indexFor(hsh);
        MapEntry<K, V> isEntry = table[index];
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        boolean b = isEntry == null;
        if (b) {
            table[index] = newEntry;
            modCount++;
            count++;
        }
        return b;
    }

    private int hash(int hashCode) {
        return Math.abs(hashCode % capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity <<= 1;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        int i = 0;
        for (MapEntry<K, V> oldEntry : oldTable) {
            if (oldEntry == null) {
                continue;
            }
            i++;
            put(oldEntry.key, oldEntry.value);
            count--;
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null && table[index].key == key ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean b = table[index] != null && table[index].key == key;
        if (b) {
            table[index] = null;
            count--;
            modCount++;
        }
        return b;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int tmp = point;
                while (tmp < capacity - 1 && table[tmp] == null) {
                    tmp++;
                }
                return tmp < capacity && table[tmp] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (point < capacity - 1) {
                    if (table[point] != null) {
                        break;
                    }
                    point++;
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}