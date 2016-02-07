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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isReachable(Point other) {
        if (this.y == other.getY() && Math.abs(this.x - other.getX()) <= 1)
            return true;
        if (this.x == other.getX() && Math.abs(this.y - other.getY()) <= 1)
            return true;

        return false;
    }
}
