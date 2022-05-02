package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> tmp = findBy(parent);
        return tmp.isPresent() && findBy(child).isEmpty() ? tmp.get().children.add(new Node<>(child)) : false;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            rsl = data.stream()
                    .filter(condition)
                    .findFirst();
            if (rsl.isPresent()) {
                break;
            }
            Queue<Node<E>> tmp = new LinkedList<>(data);
            for (int i = 0; i < data.size(); i++) {
                data.remove();
            }
            for (Node<E> e : tmp) {
                data.addAll(e.children);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        return findByPredicate(eNode -> eNode.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.value.equals(value));
    }
}