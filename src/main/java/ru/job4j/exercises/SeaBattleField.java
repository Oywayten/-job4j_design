package ru.job4j.exercises;

import java.util.*;

public class SeaBattleField {
    private int singleDeck;
    private int doubleDeck;
    private int threeDeck;
    private int fourDeck;
    private HashSet<Cell> cells;
    private boolean isOk = true;
    private boolean isHorizontal = true;
    private final LinkedList<Cell> ship = new LinkedList<>();

    public boolean checkCell(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        if (ship.size() == 1 && (x - ship.getLast().getX() == 1)) {
            isHorizontal = false;
        } else if (ship.size() == 1 && (y - ship.getLast().getY() == 1)) {
            isHorizontal = true;
        }
        if (cells.contains(new Cell(x + 1, y - 1)) || cells.contains(new Cell(x + 1, y + 1))
                || ship.size() > 1
                && (x - ship.getLast().getX() == 1 && isHorizontal
                || y - ship.getLast().getY() == 1 && !isHorizontal)
                || cells.contains(new Cell(x, y + 1))
                && cells.contains(new Cell(x + 1, y))) {
            System.out.println("Корабли размещены не правильно - не верное расположение ячеек");
            isOk = false;
        }
        ship.add(cell);
        cells.remove(cell);
        if (isOk && ship.size() > 4) {
            System.out.println("Больше 4 клеток в корабле");
            isOk = false;
        }
        if (cells.contains(new Cell(x, y + 1)) && isOk) {
            isOk = checkCell(new Cell(x, y + 1));
        } else if (cells.contains(new Cell(x + 1, y)) && isOk) {
            isOk = checkCell(new Cell(x + 1, y));
        }
        return isOk;
    }

    public boolean checkShips(LinkedHashSet<Cell> cls) {
        cells = cls;
        while (!cells.isEmpty() && isOk) {
            Iterator<Cell> iterator = cells.iterator();
            Cell c = iterator.next();
            ship.add(c);
            iterator.remove();
            int x = c.getX();
            int y = c.getY();
            if (cells.contains(new Cell(x + 1, y - 1)) || cells.contains(new Cell(x + 1, y + 1))
                    || ship.size() > 1
                    && (x - ship.getLast().getX() == 1 && isHorizontal
                    || y - ship.getLast().getY() == 1 && !isHorizontal)
                    || cells.contains(new Cell(x, y + 1))
                    && cells.contains(new Cell(x + 1, y))) {
                System.out.println("Корабли размещены не правильно - неверное расположение ячеек");
                isOk = false;
                break;
            }
            if (cells.contains(new Cell(x, y + 1))) {
                isOk = checkCell(new Cell(x, y + 1));
            } else if (cells.contains(new Cell(x + 1, y))) {
                isOk = checkCell(new Cell(x + 1, y));
            }
            if (!isOk) {
                break;
            }
            switch (ship.size()) {
                case 1 -> singleDeck++;
                case 2 -> doubleDeck++;
                case 3 -> threeDeck++;
                default -> fourDeck++;
            }
        }
        if (isOk && (singleDeck > 4 || doubleDeck > 3 || threeDeck > 2 || fourDeck > 1)) {
            System.out.println("Корабли размещены не правильно - количество");
            isOk = false;
        }
        if (isOk) {
            System.out.println("Четырехпалубных кораблей " + fourDeck);
            System.out.println("Трехпалубных кораблей " + threeDeck);
            System.out.println("Двухпалубных кораблей " + doubleDeck);
            System.out.println("Однопалубных кораблей " + singleDeck);
        }
        return isOk;
    }
}

class Cell {
    private int x;
    private int y;

    public Cell(int x1, int y1) {
        if (x1 >= 0 && x1 <= 9 && y1 >= 0 && y1 <= 9) {
            x = x1;
            y = y1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{"
                + x
                + " " + y
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}