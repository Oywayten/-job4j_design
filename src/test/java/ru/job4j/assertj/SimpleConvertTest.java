package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasAtLeastOneElementOfType(String.class)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(s -> {
                    assertThat(s.length()).isPositive();
                    assertThat(s.length()).isGreaterThan(3);
                })
                .containsAnyOf("four", "zero")
                .contains("three", Index.atIndex(2))
                .doesNotContain("zero")
                .filteredOn(s -> s.contains("f"))
                .hasSize(3)
                .last().isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .containsOnlyOnce("second")
                .element(4)
                .isNotNull();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .isNotNull()
                .containsKeys("first", "second")
                .containsValues(1, 2, 3)
                .doesNotContainValue(15)
                .containsEntry("first", 0);
    }
}