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
        if (!b) {
            if (isEntry.key.equals(key)) {
                isEntry.value = value;
                modCount++;
            }
        } else {
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
        for (MapEntry<K, V> oldEntry : oldTable) {
            put(oldEntry.key, oldEntry.value);
        }
    }

    @Override
    public V get(K key) {
//        Objects.checkIndex(indexFor(hash(key.hashCode())), capacity)
//        return table[indexFor(hash(key.hashCode()))].value;
        return (indexFor(hash(key.hashCode())) <= capacity
                || indexFor(hash(key.hashCode())) >= 0)
                && table[indexFor(hash(key.hashCode()))] != null ? table[indexFor(hash(key.hashCode()))].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean b = (indexFor(hash(key.hashCode())) <= capacity
                || indexFor(hash(key.hashCode())) >= 0)
                && table[indexFor(hash(key.hashCode()))] != null;
        if (b) {
            table[indexFor(hash(key.hashCode()))] = null;
        }
        return b;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = -1;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                K key = null;
                boolean b = false;
                int tmp = point;
                while (tmp < capacity - 1) {
                    tmp++;
                    if (table[tmp] != null) {
                        key = table[tmp].key;
                        break;
                    }
                }
                if (key != null) {
                    b = true;
                }
                return b;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (point < capacity) {
                    point++;
                    if (table[point] != null) {
                        break;
                    }
                }
                return table[point].key;
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

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            return o instanceof java.util.Map.Entry<?, ?> e
                    && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }

    }

}