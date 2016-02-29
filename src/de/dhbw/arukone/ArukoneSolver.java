package de.dhbw.arukone;

import de.dhbw.arukone.interfaces.PathInterface;
import de.dhbw.arukone.interfaces.PointInterface;
import de.dhbw.arukone.old.ArukoneBoard;
import de.dhbw.arukone.old.Path;
import de.dhbw.arukone.old.Point;

public class ArukoneSolver {
    public static long iterations = 0;

//    public void presolve (ArukoneBoard board) {
//        boolean step = true;
//        while (step) {
//            step = false;
//
//            for (Path path : board.getPaths()) {
//                if (!path.isComplete()) {
//                    List<Point> freeNeighbours = board.getFreeNeighbours(path.getLastPointFromStart());
//                    if (freeNeighbours.size() == 1) {
//                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
//                        step = true;
//                    }
//                    if (path.isComplete()) {
//                        continue;
//                    }
//                    freeNeighbours.clear();
//                    freeNeighbours = board.getFreeNeighbours(path.getLastPointFromEnd());
//                    if (freeNeighbours.size() == 1) {
//                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
//                        step = true;
//                    }
//                }
//            }
//        }
//    }

    public boolean solve (ArukoneBoard board, int pathId) {
        if (board.isSolved()) {
            return true; // board solved
        } else {
            iterations++;
            if (board.getPathById(pathId).isComplete()) {
                return solve(board, pathId + 1); // if current path is complete, solve the next path
            } else { // if current path is not complete, try to complete it
                boolean next = false;
                int checked = 0;
                PathInterface currentPath = board.getPathById(pathId);
                PointInterface point = currentPath.getLastSetWaypoint();
                PointInterface up = point.up();
                PointInterface right = point.right();
                PointInterface down = point.down();
                PointInterface left = point.left();

                if (board.isFree(up)) {
                    checked++;
                    board.addWaypointByPathId(pathId, up);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(right)) {
                    checked++;
                    board.addWaypointByPathId(pathId, right);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(down)) {
                    checked++;
                    board.addWaypointByPathId(pathId, down);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(left)) {
                    checked++;
                    board.addWaypointByPathId(pathId, left);
                    next = solve(board, pathId);
                }
                if (board.getFreeNeighbours(point).size() - checked == 0) {
                    board.removeLastSetWaypoint();
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
}
