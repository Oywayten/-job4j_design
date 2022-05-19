package ru.job4j.exercises;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SeaBattleFieldTest {
    @Test
    public void whenBad1ShipisFalse() {
        LinkedHashSet<Cell> ships =
                new LinkedHashSet<>(
                        Arrays.asList(
                                new Cell(3, 3),
                                new Cell(4, 2),
                                new Cell(5, 1),
                                new Cell(5, 2),
                                new Cell(6, 1),
                                new Cell(7, 1),
                                new Cell(7, 2),
                                new Cell(8, 1),
                                new Cell(2, 2),
                                new Cell(9, 2))
                );
        SeaBattleField seaBattleField = new SeaBattleField();
        Assert.assertFalse(seaBattleField.checkShips(ships));
    }

    @Test
    public void whenMiniGisFalse() {
        LinkedHashSet<Cell> ships =
                new LinkedHashSet<>(
                        Arrays.asList(
                                new Cell(3, 3),
                                new Cell(3, 4),
                                new Cell(4, 3))
                );
        SeaBattleField seaBattleField = new SeaBattleField();
        Assert.assertFalse(seaBattleField.checkShips(ships));
    }

    @Test
    public void when4InVerticalLineIsTrue() {
        LinkedHashSet<Cell> ships =
                new LinkedHashSet<>(
                        Arrays.asList(
                                new Cell(3, 3),
                                new Cell(4, 3),
                                new Cell(5, 3),
                                new Cell(6, 3))
                );
        SeaBattleField seaBattleField = new SeaBattleField();
        Assert.assertTrue(seaBattleField.checkShips(ships));
    }

    @Test
    public void when4InVerticalAnd4InHorisontalIsFalse() {
        LinkedHashSet<Cell> ships =
                new LinkedHashSet<>(
                        Arrays.asList(
                                new Cell(3, 3),
                                new Cell(4, 3),
                                new Cell(5, 3),
                                new Cell(6, 3),
                                new Cell(5, 5),
                                new Cell(5, 6),
                                new Cell(5, 7),
                                new Cell(5, 8))
                );
        SeaBattleField seaBattleField = new SeaBattleField();
        Assert.assertFalse(seaBattleField.checkShips(ships));
    }

    @Test
    public void when5InVerticalLineIsFalse() {
        LinkedHashSet<Cell> ships =
                new LinkedHashSet<>(
                        Arrays.asList(
                                new Cell(3, 3),
                                new Cell(4, 3),
                                new Cell(5, 3),
                                new Cell(6, 3),
                                new Cell(7, 3))
                );
        SeaBattleField seaBattleField = new SeaBattleField();
        Assert.assertFalse(seaBattleField.checkShips(ships));
    }

    @Test
    public void when4InVerticalLineAnd1InDiagonalIsFalse() {
        LinkedHashSet<Cell> ships =
                new LinkedHashSet<>(
                        Arrays.asList(
                                new Cell(3, 3),
                                new Cell(4, 3),
                                new Cell(5, 3),
                                new Cell(6, 3),
                                new Cell(7, 4))
                );
        SeaBattleField seaBattleField = new SeaBattleField();
        Assert.assertFalse(seaBattleField.checkShips(ships));
    }
}