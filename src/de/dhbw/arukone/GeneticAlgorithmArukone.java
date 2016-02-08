package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public class GeneticAlgorithmArukone {
    private ArukoneBoard board;

    public GeneticAlgorithmArukone(ArukoneBoard board) {
        this.board = board;
    }

    public void presolve() {
        boolean step = true;
        while (step) {
            step = false;

            for (Path path : this.board.getPaths()) {
                if (!path.isComplete()) {
                    List<Point> freeNeighbours = getNeighbours(path.getLastPointFromStart());
                    if (freeNeighbours.size() == 1) {
                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
                        step = true;
                    }
                    freeNeighbours.clear();
                    freeNeighbours = getNeighbours(path.getLastPointFromEnd());
                    if (freeNeighbours.size() == 1) {
                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
                        step = true;
                    }
                }
            }
        }
    }

    private List<Point> getNeighbours(Point point) {
        List<Point> neighbours = new ArrayList<>(4);

        Point left = point.left();
        Point right = point.right();
        Point up = point.up();
        Point down = point.down();

        if (left != null && board.isFree(left)) {
            neighbours.add(left);
        }
        if (right != null && board.isFree(right)) {
            neighbours.add(right);
        }
        if (up != null && board.isFree(up)) {
            neighbours.add(up);
        }
        if (down != null && board.isFree(down)) {
            neighbours.add(down);
        }

        return neighbours;
    }
}
