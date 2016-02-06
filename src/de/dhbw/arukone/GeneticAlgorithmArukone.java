package de.dhbw.arukone;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public class GeneticAlgorithmArukone {
    ArukoneBoard board;

    public GeneticAlgorithmArukone(ArukoneBoard board) {
        this.board = board;
    }

    public void solve() {
        int size = board.getEdgeLength();

        boolean changedSomething = true;
        while (changedSomething) {
            changedSomething = false;
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    int value = board.getValueAt(x, y);
                    if (value == 0) {
                        continue;
                    }

                    Direction dir = checkDirection(x, y);
                    if (dir.getPossibleDirectionCount() == 1) {
                        changedSomething = true;
                        if (dir.north)
                            board.setValueAt(x-1, y, value);
                        if (dir.south)
                            board.setValueAt(x+1, y, value);
                        if (dir.east)
                            board.setValueAt(x, y+1, value);
                        if (dir.west)
                            board.setValueAt(x, y-1, value);
                        System.out.println(board.advancedToString());
                    }
                }
            }
        }
    }

    public Direction checkDirection(int x, int y) {
        Direction dir = new Direction();

        if ((x-1) >= 0 && this.board.getValueAt(x-1, y) == 0)
            dir.north = true;
        if ((x+1) < this.board.getEdgeLength() && this.board.getValueAt(x+1, y) == 0)
            dir.south = true;
        if ((y-1) >= 0 && this.board.getValueAt(x, y-1) == 0)
            dir.west = true;
        if ((y+1) < this.board.getEdgeLength() && this.board.getValueAt(x, y+1) == 0)
            dir.east = true;

        return dir;
    }

    class Direction {
        boolean north, south, east, west;

        Direction() {
            north = false;
            south = false;
            east = false;
            west = false;
        }

        int getPossibleDirectionCount() {
            int i = 0;
            if (north) i++;
            if (south) i++;
            if (west) i++;
            if (east) i++;
            return i;
        }
    }
}
