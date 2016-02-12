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

    public boolean solvePath(Path path) {
        return solvePath(path, 0);
    }

    public boolean solvePath(Path path, int skip) {
        if (skip > 0) {
            boolean needTest = true;
            while (needTest) {
                Point removed = board.removeLastSetWaypointByPathId(path.getId());
                if (removed != null) {
                    Point current = path.getLastSetWaypoint();
                    Point up = current.up();
                    Point down = current.down();
                    Point left = current.left();
                    Point right = current.right();


                    if (board.isFree(up) && !path.isBlocked(up)) {
                        skip--;
                    }
                    if (board.isFree(right) && !path.isBlocked(right)) {
                        if (skip == 0) {
                            board.addWaypointByPathId(path.getId(), right);
                            needTest = false;
                            return solvePath(path, skip);
                        } else {
                            skip--;
                        }
                    }
                    if (needTest && board.isFree(down) && !path.isBlocked(down)) {
                        if (skip == 0) {
                            board.addWaypointByPathId(path.getId(), down);
                            needTest = false;
                            return solvePath(path, skip);
                        } else {
                            skip--;
                        }
                    }
                    if (needTest && board.isFree(left) && !path.isBlocked(left)) {
                        if (skip == 0) {
                            board.addWaypointByPathId(path.getId(), left);
                            needTest = false;
                            return solvePath(path, skip);
                        } else {
                            skip--;
                        }
                    }
                }
            }
        }

        if (!path.isComplete()) {
            Point lastPoint = path.getLastSetWaypoint();
            Point up = lastPoint.up();
            Point down = lastPoint.down();
            Point left = lastPoint.left();
            Point right = lastPoint.right();

            if (board.isFree(up) && !path.isBlocked(up)) {
                board.addWaypointByPathId(path.getId(), up);
                return solvePath(path, skip);
            } else if (board.isFree(right) && !path.isBlocked(right)) {
                board.addWaypointByPathId(path.getId(), right);
                return solvePath(path, skip);
            } else if (board.isFree(down) && !path.isBlocked(down)) {
                board.addWaypointByPathId(path.getId(), down);
                return solvePath(path, skip);
            } else if (board.isFree(left) && !path.isBlocked(left)) {
                board.addWaypointByPathId(path.getId(), left);
                return solvePath(path, skip);
            } else {
                Point point = board.removeLastSetWaypointByPathId(path.getId());
                if (point != null) {
                    path.addBlockedPoint(point);
                    return solvePath(path, skip);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
