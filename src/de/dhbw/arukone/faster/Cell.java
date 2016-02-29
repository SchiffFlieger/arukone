package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PointInterface;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class Cell {
    private final int x;
    private final int y;
    private final int boardSize;
    private boolean fixed;
    private int value;

    public Cell(int x, int y, int boardSize) {
        this(x, y, boardSize, false, 0);
    }

    @Deprecated
    public Cell(int x, int y, int boardSize, boolean fixed, int value) {
        this.x = x;
        this.y = y;
        this.boardSize = boardSize;
        this.fixed = fixed;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (!fixed) {
            this.value = value;
        }
    }

    public int getX () {
        return this.x;
    }

    public int getY () {
        return this.y;
    }

    public boolean isNeighbour (Cell other) {
        int diffY = Math.abs(this.y - other.getY());
        int diffX = Math.abs(this.x - other.getX());
        int diffGes = Math.abs(diffX + diffY);
        return diffGes == 1;
    }

    public boolean isConnected(Cell other) {
        if (other == null) {
            return false;
        }
        if (isNeighbour(other)) {
            return this.getValue() == other.getValue();
        }
        return false;
    }

    public Cell left () {
        if (this.y <= 0)
            return null;
        return new Cell(this.x, this.y - 1, this.boardSize);
    }

    public Cell right () {
        if (this.y >= boardSize - 1)
            return null;
        return new Cell(this.x, this.y + 1, boardSize);
    }

    public Cell down () {
        if (this.x <= 0) {
            return null;
        }
        return new Cell(this.x - 1, this.y, this.boardSize);
    }

    public Cell up () {
        if (this.x >= boardSize - 1) {
            return null;
        }
        return new Cell(this.x + 1, this.y, this.boardSize);
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed() {
        this.fixed = true;
    }
}
