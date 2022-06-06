package ru.job4j.exercises.serialization;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("serial"))) {
            MyClass myClass = new MyClass("Hello", -7, 2.7e10);
            System.out.println("myClass: " + myClass);
            stream.writeObject(myClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("serial"))) {
            MyClass myClass = (MyClass) stream.readObject();
            System.out.println("myClass: " + myClass);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
