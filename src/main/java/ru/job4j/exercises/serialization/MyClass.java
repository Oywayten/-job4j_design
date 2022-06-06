package ru.job4j.exercises.serialization;

import java.io.Serializable;

public class MyClass implements Serializable {
    String s;
    int i;
    transient double d;
    static final long serialVersionUID = 11;

    public MyClass(String s, int i, double d) {
        this.s = s;
        this.i = i;
        this.d = d;
    }

    @Override
    public String toString() {
        return "MyClass{" + "s='" + s + '\''
                + ", i=" + i + ", d=" + d + '}';
    }
}
