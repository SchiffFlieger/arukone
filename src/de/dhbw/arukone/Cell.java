package de.dhbw.arukone;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class Cell {
    private final int x;
    private final int y;
    private boolean fixed;
    private int value;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.fixed = false;
        this.value = 0;
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

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed() {
        this.fixed = true;
    }

    @Override
    public String toString() {
        return String.format("Cell [%d|%d] value: %d fixed: %b", x, y, value, fixed);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;
        if (fixed != cell.fixed) return false;
        return value == cell.value;

    }

    @Override
    public int hashCode () {
        int result = x;
        result = 98993 * result + y;
        result = 98993 * result + (fixed ? 1 : 0);
        result = 98993 * result + value;
        return result;
    }
}
