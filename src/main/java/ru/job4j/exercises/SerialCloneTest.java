package ru.job4j.exercises;

import java.io.*;
import java.time.LocalDate;

/**
 * @author Oywayten
 * @version 1.01 2022-06-09
 */

public class SerialCloneTest {
    /**
     * * @version 1.01 2022-06-09
     * * @author Oywayten
     * <p>
     * Клонируем и модифицируем объект harry
     *
     * @param args параметры из консоли, если требуются
     */
    public static void main(String[] args)
            throws CloneNotSupportedException {
        Employee harry = new Employee("Harry Hacker",
                35000, 1989, 10, 1);
        Employee harry2 = (Employee) harry.clone();
        harry.raiseSalary(10);
        System.out.println(harry);
        System.out.println(harry2);
    }
}

/**
 * Класс в методе которого применяется сериализация.
 */
class SerialClonable implements Cloneable, Serializable {

    /**
     * <pre> Cохранить объект в массиве байтов.
     *     {@code
     *      ByteArrayOutputStream bout = new ByteArrayOutputStream();
     *      try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
     *          out.writeObject(this);
     *         }}
     * </pre>
     * <pre> Ввести клон объекта из массива байтов.
     *     {@code
     *      try (ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
     *          ObjectInputStream in = new ObjectInputStream(bin);
     *          return in.readObject();
     *             }}
     * </pre>
     *
     * @return возвращает клонированный объект
     * @throws CloneNotSupportedException если клонирование не поддерживается
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }
            try (ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
                ObjectInputStream in = new ObjectInputStream(bin);
                return in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}

/**
 * Переопределяемый класс для расширения класса SerialCloneable
 */
class Employee extends SerialClonable {
    private final String name;
    private final LocalDate hireDay;
    private double salary;

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    /**
     * Поднимает зарплату данному работнику
     *
     * @param byPercent Процент повышения зарплаты
     */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += salary;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "{" + "name='" + name + '\''
                + ", salary=" + salary
                + ", hireDay=" + hireDay
                + '}';
    }
}