package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PointInterface;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class Cell implements PointInterface {
    private final int x;
    private final int y;
    private final int boardSize;
    private final boolean fixed;
    private int value;

    public Cell(int x, int y, int boardSize) {
        this(x, y, boardSize, false, 0);
    }

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

    @Override
    public int getX () {
        return this.x;
    }

    @Override
    public int getY () {
        return this.y;
    }

    @Override
    public boolean isNeighbour (PointInterface other) {
        int diffY = Math.abs(this.y - other.getY());
        int diffX = Math.abs(this.x - other.getX());
        int diffGes = Math.abs(diffX + diffY);
        return diffGes == 1;
    }

    @Override
    public PointInterface left () {
        if (this.y <= 0)
            return null;
        return new Cell(this.x, this.y - 1, this.boardSize);
    }

    @Override
    public PointInterface right () {
        if (this.y >= boardSize - 1)
            return null;
        return new Cell(this.x, this.y + 1, boardSize);
    }

    @Override
    public PointInterface down () {
        if (this.x <= 0) {
            return null;
        }
        return new Cell(this.x - 1, this.y, this.boardSize);
    }

    @Override
    public PointInterface up () {
        if (this.x >= boardSize - 1) {
            return null;
        }
        return new Cell(this.x + 1, this.y, this.boardSize);
    }
}
