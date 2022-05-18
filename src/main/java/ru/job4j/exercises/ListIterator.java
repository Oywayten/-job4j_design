package ru.job4j.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Link2 {
    public int item;
    public Link2 next;

    public Link2(int i) {
        item = i;
    }

    public void displayLink2() {
        System.out.print(item + " ");
    }
}

class LinkList2 {
    private Link2 first;

    public Link2 getFirst() {
        return first;
    }

    public void setFirst(Link2 first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public ListIterator getIterator() {
        return new ListIterator(this);
    }

    public void displayList() {
        Link2 current = first;
        System.out.print("List is: ");
        while (current != null) {
            current.displayLink2();
            current = current.next;
        }
        System.out.println();
    }
}

public class ListIterator {
    private Link2 current;
    private Link2 previous;
    private LinkList2 ourList;

    public ListIterator(LinkList2 newList) {
        ourList = newList;
        reset();
    }

    public void reset() {
        current = ourList.getFirst();
        previous = null;
    }

    public boolean atEnd() {
        return current.next == null;
    }

    public void nextLink() {
        previous = current;
        current = current.next;
    }

    public Link2 getCurrent() {
        return current;
    }

    public void insertAfter(int i) {
        Link2 newLink = new Link2(i);
        if (ourList.isEmpty()) {
            ourList.setFirst(newLink);
            current = ourList.getFirst();
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();
        }
    }

    public void insertBefore(int i) {
        Link2 newLink = new Link2(i);
        if (previous == null) {
            newLink.next = current;
            ourList.setFirst(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    public int deleteCurrent() {
        int tmpItem = current.item;
        if (previous == null) {
            ourList.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }
        return tmpItem;
    }
}

class ListIterApp {
    public static void main(String[] args) throws IOException {
        int value;
        LinkList2 theList = new LinkList2();
        ListIterator iter1 = theList.getIterator();
        iter1.insertAfter(20);
        iter1.insertAfter(40);
        iter1.insertAfter(80);
        iter1.insertAfter(60);
        while (true) {
            System.out.println("Enter first letter of show. reset.");
            System.out.println("next. get. before. after. delete: ");
            System.out.flush();
            int choice = getChar();
            switch (choice) {
                case 's':
                    if (!theList.isEmpty()) {
                        theList.displayList();
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'r':
                    iter1.reset();
                    break;
                case 'n':
                    if (!theList.isEmpty() && !iter1.atEnd()) {
                        iter1.nextLink();
                    } else {
                        System.out.println("Can't go to next link");
                    }
                    break;
                case 'g':
                    if (!theList.isEmpty()) {
                        value = iter1.getCurrent().item;
                        System.out.println("Renurned " + value);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'b':
                    System.out.println("Enter value to insert");
                    System.out.flush();
                    value = getInt();
                    iter1.insertBefore(value);
                    break;
                case 'a':
                    System.out.println("Enter value to insert");
                    System.out.flush();
                    value = getInt();
                    iter1.insertAfter(value);
                    break;
                case 'd':
                    if (!theList.isEmpty()) {
                        value = iter1.deleteCurrent();
                        System.out.println("Deleted " + value);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(reader);
        return bfr.readLine();
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}