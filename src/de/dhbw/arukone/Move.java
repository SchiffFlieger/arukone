package de.dhbw.arukone;

public class Move {
    private final Point point;
    private final Direction dir;
    private final int pathId;

    public Move(Point point, Direction dir, int pathId) {
        this.point = point;
        this.dir = dir;
        this.pathId = pathId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        return pathId == move.pathId && (point != null ? point.equals(move.point) : move.point == null && dir == move.dir);

    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (dir != null ? dir.hashCode() : 0);
        result = 31 * result + pathId;
        return result;
    }

    public enum Direction {
        UP(0), RIGHT(1), DOWN(2), LEFT(3);

        private final int dir;

        Direction(int dir) {
            this.dir = dir;
        }

        public int getDir() {
            return dir;
        }


    }
}
