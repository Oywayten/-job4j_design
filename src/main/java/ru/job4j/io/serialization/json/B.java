package ru.job4j.io.serialization.json;

import org.json.JSONObject;

public class B {
    private ru.job4j.io.serialization.json.A a;

    public static void main(String[] args) {
        ru.job4j.io.serialization.json.A a = new ru.job4j.io.serialization.json.A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));
    }

    public ru.job4j.io.serialization.json.A getA() {
        return a;
    }

    public void setA(ru.job4j.io.serialization.json.A a) {
        this.a = a;
    }
}