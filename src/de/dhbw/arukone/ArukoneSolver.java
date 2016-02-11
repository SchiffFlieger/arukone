package de.dhbw.arukone;

import java.util.LinkedList;
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

    public void solvePath(Path path) {
        this.solvePath(path, new LinkedList<>());
    }

    private void solvePath(Path path, List<Point> blockedPoints) {
        if (!path.isComplete()) {
            Point lastPoint = path.getLastPointFromStart();
            Point up = lastPoint.up();
            Point down = lastPoint.down();
            Point left = lastPoint.left();
            Point right = lastPoint.right();

            if (board.isFree(up) && !blockedPoints.contains(up)) {
                board.addWaypointByPathId(path.getId(), up);
                solvePath(path);
            } else if (board.isFree(right) && !blockedPoints.contains(right)) {
                board.addWaypointByPathId(path.getId(), right);
                solvePath(path);
            } else if (board.isFree(down) && !blockedPoints.contains(down)) {
                board.addWaypointByPathId(path.getId(), down);
                solvePath(path);
            } else if (board.isFree(left) && !blockedPoints.contains(left)) {
                board.addWaypointByPathId(path.getId(), left);
                solvePath(path);
            } else {
                Point point = path.removeLastSetWaypoint();
                blockedPoints.add(point);
                solvePath(path, blockedPoints);
            }
        }
    }


}
