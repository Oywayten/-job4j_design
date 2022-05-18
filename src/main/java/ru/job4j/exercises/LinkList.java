package ru.job4j.exercises;

class Link {
    private int item;
    private Link next;

    public Link(int i) {
        item = i;
    }

    public void displayLink() {
        System.out.print(item + " ");
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }
}

public class LinkList {
    private Link first;

    public LinkList() {
        first = null;
    }

    public void insertFirst(int item) {
        Link link = new Link(item);
        link.setNext(first);
        first = link;
    }

    public int deleteFirst() {
        int tmp = first.getItem();
        first = first.getNext();
        return tmp;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void displayList() {
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
        System.out.println();
    }
}

class LinkStack {
    LinkList list;

    public LinkStack() {
        list = new LinkList();
    }

    public void push(int i) {
        list.insertFirst(i);
    }

    public int pop() {
        return list.deleteFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void displayStack() {
        System.out.print("Stack (top --> bottom): ");
        list.displayList();
    }
}

class LinkListApp {
    public static void main(String[] args) {
        LinkStack stack = new LinkStack();
        stack.push(20);
        stack.push(40);
        stack.displayStack();
        stack.push(60);
        stack.push(80);
        stack.displayStack();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.displayStack();
    }
}