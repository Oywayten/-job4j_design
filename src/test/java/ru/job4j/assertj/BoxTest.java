package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

/**
 * Created by Oywayten on 30.11.2022.
 */
class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").isNotEmpty();
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(5, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object").isNotNull();
    }

    @Test
    void whenVerticesIs0() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0).isZero();
    }

    @Test
    void whenVerticesIsMinus1() {
        Box box = new Box(5, 10);
        int vertices = box.getNumberOfVertices();
        System.out.println(vertices);
        assertThat(vertices).isEqualTo(-1).isNegative();
    }

    @Test
    void whenAreaIsCloseTo6Dot92() {
        Box box = new Box(4, 2);
        Double area = box.getArea();
        assertThat(area).isEqualTo(6.92, withPrecision(0.009)).isGreaterThan(6);
    }

    @Test
    void whenAreaIsCloseTo12Dot56() {
        Box box = new Box(0, 1);
        Double area = box.getArea();
        assertThat(area).isCloseTo(12.56, withPrecision(0.007)).isBetween(12.00, 13.00);
    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(5, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }
}