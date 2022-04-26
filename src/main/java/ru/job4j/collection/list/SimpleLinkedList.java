package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> last;
    private Node<E> first;
    private int size;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E item) {
        final Node<E> prev = last;
        final Node<E> newNode = new Node<>(prev, item, null);
        last = newNode;
        if (prev == null) {
            first = newNode;
        } else {

            prev.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = index; i < size - 1; i++) {
                x = x.prev;
            }
        }
        return x.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> tmp = first;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return tmp != null && tmp.item != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> x = tmp;
                tmp = tmp.next;
                return x.item;
            }
        };
    }
}