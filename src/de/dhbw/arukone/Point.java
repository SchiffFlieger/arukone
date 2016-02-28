package de.dhbw.arukone;

public class Point {
    private final int x, y;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
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

    public boolean isReachable (Point other) {
        int diffY = Math.abs(this.y - other.y);
        int diffX = Math.abs(this.x - other.x);
        int diffGes = Math.abs(diffX + diffY);
        return diffGes == 1;

    }

    public Point left () {
        if (this.y <= 0)
            return null;
        return new Point(this.x, this.y - 1);
    }

    public Point right () {
        if (this.y >= Launcher.size - 1)
            return null;
        return new Point(this.x, this.y + 1);
    }

    public Point up () {
        if (this.x <= 0) {
            return null;
        }
        return new Point(this.x - 1, this.y);
    }

    public Point down () {
        if (this.x >= Launcher.size - 1) {
            return null;
        }
        return new Point(this.x + 1, this.y);
    }

    @Override
    public String toString () {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
