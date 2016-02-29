package de.dhbw.arukone;

public class FastArukoneSolver {
    public static long iterations = 0;


    public boolean solve (FastArukoneBoard board, int pathId) {
        if (board.isSolved()) {
            return true; // board solved
        } else {
            iterations++;
            if (board.isPathComplete(pathId)) {
                return solve(board, pathId + 1);
            } else {
                boolean next = false;
                int checked = 0;
                Cell cell = board.getActivePoint(pathId);
                Cell up = board.up(cell);
                Cell right = board.right(cell);
                Cell down = board.down(cell);
                Cell left = board.left(cell);

                if (board.isFree(up)) {
                    checked++;
                    board.setWaypoint(cell, up);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(right)) {
                    checked++;
                    board.setWaypoint(cell, right);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(down)) {
                    checked++;
                    board.setWaypoint(cell, down);
                    next = solve(board, pathId);
                }
                if (!next && board.isFree(left)) {
                    checked++;
                    board.setWaypoint(cell, left);
                    next = solve(board, pathId);
                }
                if (board.getFreeNeighbours(cell).size() - checked == 0) {
                    board.removeLastSetWaypoint();
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
}
