package de.dhbw.arukone;

/**
 * Represents a single field of an {@link ArukoneBoard}.
 */
public class Cell {
    /**
     * x-coordinate of the cell
     */
    private final int x;

    /**
     * y-coordinate of the cell
     */
    private final int y;

    /**
     * true if the cell cannot be changed
     */
    private boolean fixed;

    /**
     * path id if the cell belongs to a path, else 0
     */
    private int value;

    /**
     * next cell in the path
     */
    private Cell next;

    /**
     * previous cell in the path
     */
    private Cell previous;

    /**
     * Basic contructor for an empty {@link Cell}.
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     */
    public Cell (int x, int y) {
        this.x = x;
        this.y = y;
        this.fixed = false;
        this.value = 0;
        this.next = null;
    }

    /**
     * Checks if the given cell is a neighbor of the current cell.
     * @param other cell to check
     * @return true if neighbor, else false
     */
    public boolean isNeighbour (Cell other) {
        int diffY = Math.abs(this.y - other.getY());
        int diffX = Math.abs(this.x - other.getX());
        int diffGes = Math.abs(diffX + diffY);
        return diffGes == 1;
    }

    /**
     * Checks if the given cell is connected to the current cell. It is connected if both cells are neighbors and belong to the same path.
     * @param other cell to check
     * @return true if connected, else false
     */
    public boolean isConnected (Cell other) {
        if (other == null) {
            return false;
        }
        if (isNeighbour(other)) {
            return this.getValue() == other.getValue();
        }
        return false;
    }

    public int getValue () {
        return this.value;
    }

    public void setValue (int value) {
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

    public boolean isFixed () {
        return fixed;
    }

    public void setFixed () {
        this.fixed = true;
    }

    public Cell getNext () {
        return next;
    }

    public void setNext (Cell next) {
        this.next = next;
    }

    public Cell getPrevious () {
        return previous;
    }

    public void setPrevious (Cell previous) {
        this.previous = previous;
    }

    @Override
    public String toString () {
        return String.format("Cell [%d|%d] value: %d fixed: %b", x, y, value, fixed);
    }

    @Override
    public boolean equals (Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Cell cell = (Cell) other;

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
