package ru.job4j.exercises;

import java.io.*;

public class TestSerial implements Serializable {
    private final int version = 100;
    private final int count = 0;

    @Override
    public String toString() {
        return "TestSerial{"
                + "version=" + version
                + ", count=" + count
                + '}';
    }
}

class InSerial {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("temp.out");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            TestSerial ts = new TestSerial();
            oos.writeObject(ts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class OutSerial {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("temp.out");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            TestSerial ts = (TestSerial) ois.readObject();
            System.out.println(ts);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}