package de.dhbw.arukone.solver;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.Cell;

/**
 * Solver for {@link ArukoneBoard}.
 */
public class ArukoneSolver {
    public static long iterations = 0;

    /**
     * Solves a given {@link ArukoneBoard} using a backtracking algorithm.
     * @param board {@link ArukoneBoard} to solve
     * @return true if board is solved, else false.
     */
    public boolean solve (ArukoneBoard board) {
        return solve(board, 1);
    }

    /**
     * Solves a given {@link ArukoneBoard} using a backtracking algorithm.
     * @param board {@link ArukoneBoard} to solve
     * @param pathId current path to complete
     * @return true if board is solved, else false.
     */
    private boolean solve (ArukoneBoard board, int pathId) {
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
