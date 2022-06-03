package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Truck {
    private final boolean isTipper;
    private final int loadCapacity;
    private final String model;
    private final Contact contact;
    private final String[] passengers;

    public Truck(boolean isTipper, int loadCapacity, String model, Contact contact, String[] passengers) {
        this.isTipper = isTipper;
        this.loadCapacity = loadCapacity;
        this.model = model;
        this.contact = contact;
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Truck{" + "isTipper=" + isTipper + ", loadCapacity=" + loadCapacity + ", model='" + model
                + '\'' + ", contact=" + contact + ", passengers=" + Arrays.toString(passengers) + '}';
    }
}
