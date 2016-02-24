package de.dhbw.arukone;

import java.util.List;

public class ArukoneSolver {

    private static long iterations = 0;

    public static void reset() {
        iterations = 0;
    }

    public static long getIterations() {
        return iterations;
    }

    public void presolve(ArukoneBoard board) {
        boolean step = true;
        while (step) {
            step = false;

            for (Path path : board.getPaths()) {
                if (!path.isComplete()) {
                    List<Point> freeNeighbours = board.getFreeNeighbours(path.getLastPointFromStart());
                    if (freeNeighbours.size() == 1) {
                        board.addWaypointByPathId(path.getId(), freeNeighbours.get(0));
                        step = true;
                    }
                    if (path.isComplete()) {
                        continue;
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

    public ArukoneBoard solve(ArukoneBoard board, int pathId) {
        System.out.println("------------------");
        System.out.println(board);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (board.isSolved()) {
            return board; // board solved
        } else if (!board.isSolveable()) {
            return null;
        } else {
            iterations++;
//            presolve(board);
            if (board.getPathById(pathId).isComplete()) {
                return solve(new ArukoneBoard(board), pathId + 1); // if current path is complete, solve the next path
            } else { // if current path is not complete, try to complete it
                ArukoneBoard next = null;
                Path currentPath = board.getPathById(pathId);
                Point point = currentPath.getLastSetWaypoint();
                Point up = point.up();
                Point right = point.right();
                Point down = point.down();
                Point left = point.left();

                if (board.isFree(up)) {
                    next = new ArukoneBoard(board);
                    next.addWaypointByPathId(pathId, up);
                    next = solve(next, pathId);
                }
                if (next == null && board.isFree(right)) {
                    next = new ArukoneBoard(board);
                    next.addWaypointByPathId(pathId, right);
                    next = solve(next, pathId);
                }
                if (next == null && board.isFree(down)) {
                    next = new ArukoneBoard(board);
                    next.addWaypointByPathId(pathId, down);
                    next = solve(next, pathId);
                }
                if (next == null && board.isFree(left)) {
                    next = new ArukoneBoard(board);
                    next.addWaypointByPathId(pathId, left);
                    next = solve(next, pathId);
                }
                if (!board.hasFreeNeighbours(point)) {
                    return null;
                } else {
                    return next;
                }
            }
        }
    }
}
