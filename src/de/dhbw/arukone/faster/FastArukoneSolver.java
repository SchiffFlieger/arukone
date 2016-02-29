package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PathInterface;
import de.dhbw.arukone.interfaces.PointInterface;
import de.dhbw.arukone.old.ArukoneBoard;

public class FastArukoneSolver {
    public static long iterations = 0;


    public boolean solve (FastArukoneBoard board, int pathId) {
        if (board.isSolved()) {
            return true; // board solved
        } else {
            iterations++;
            if (board.isPathComplete(pathId)) {
                return solve(board, pathId + 1); // if current path is complete, solve the next path
            } else { // if current path is not complete, try to complete it
                boolean next = false;
                int checked = 0;
                Cell point = board.getActivePoint(pathId);
                Cell up = point.up();
                Cell right = point.right();
                Cell down = point.down();
                Cell left = point.left();

                if (board.isFree(up)) {
                    checked++;
                    board.setWaypoint(up.getX(), up.getY(), pathId);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(right)) {
                    checked++;
                    board.setWaypoint(right.getX(), right.getY(), pathId);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(down)) {
                    checked++;
                    board.setWaypoint(down.getX(), down.getY(), pathId);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(left)) {
                    checked++;
                    board.setWaypoint(left.getX(), left.getY(), pathId);
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
