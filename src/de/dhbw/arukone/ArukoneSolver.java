package de.dhbw.arukone;

import java.util.List;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public class ArukoneSolver {
    private ArukoneBoard board;

    public ArukoneSolver(ArukoneBoard board) {
        this.board = board;
    }

    public void presolve() {
        boolean step = true;
        while (step) {
            step = false;

            for (Path path : this.board.getPaths()) {
                if (!path.isComplete()) {
                    List<Point> freeNeighbours = board.getFreeNeighbours(path.getLastPointFromStart());
                    if (freeNeighbours.size() == 1) {
                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
                        step = true;
                    }
                    freeNeighbours.clear();
                    freeNeighbours = board.getFreeNeighbours(path.getLastPointFromEnd());
                    if (freeNeighbours.size() == 1) {
                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
                        step = true;
                    }
                }
            }
        }
    }

    public void solveRecursively() {

    }

    private boolean solveRecursively(List<Path> paths, int index) {

        return false;
    }

    private boolean solvePath(Path path) {
        Point lastFromStart = path.getLastPointFromStart();
        if (board.hasFreeNeighbours(lastFromStart)) {

        }

        return true;
    }


}
