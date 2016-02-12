package de.dhbw.arukone;

/**
 * Created by karsten on 06.02.16.
 */
public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public boolean isReachable(Point other) {
        if (this.equals(other))
            return false;

        if (this.y == other.getY() && Math.abs(this.x - other.getX()) <= 1)
            return true;
        if (this.x == other.getX() && Math.abs(this.y - other.getY()) <= 1)
            return true;

        return false;
    }

    public Point left() {
        if (this.y <= 0)
            return null;
        return new Point(this.x, this.y-1);
    }

    public Point right() {
        if (this.y >= Configuration.instance.size-1)
            return null;
        return new Point(this.x, this.y+1);
    }

    public Point up() {
        if (this.x <= 0) {
            return null;
        }
        return new Point(this.x-1, this.y);
    }

    public Point down() {
        if (this.x >= Configuration.instance.size-1) {
            return null;
        }
        return new Point(this.x+1, this.y);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
