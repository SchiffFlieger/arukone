package de.dhbw.arukone.old;

import de.dhbw.arukone.Launcher;
import de.dhbw.arukone.interfaces.PointInterface;

public class Point implements PointInterface {
    private final int x, y;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX () {
        return x;
    }

    @Override
    public int getY () {
        return y;
    }

    @Override
    public boolean isNeighbour (PointInterface other) {
        int diffY = Math.abs(this.y - other.getY());
        int diffX = Math.abs(this.x - other.getX());
        int diffGes = Math.abs(diffX + diffY);
        return diffGes == 1;
    }

    @Override
    public Point left () {
        if (this.y <= 0)
            return null;
        return new Point(this.x, this.y - 1);
    }

    @Override
    public Point right () {
        if (this.y >= Launcher.size - 1)
            return null;
        return new Point(this.x, this.y + 1);
    }

    @Override
    public Point up () {
        if (this.x <= 0) {
            return null;
        }
        return new Point(this.x - 1, this.y);
    }

    @Override
    public Point down () {
        if (this.x >= Launcher.size - 1) {
            return null;
        }
        return new Point(this.x + 1, this.y);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode () {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString () {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
