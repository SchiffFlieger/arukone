package de.dhbw.arukone;

import java.util.List;

public class ArukoneSolver implements Runnable {

    public static long iterations = 0;

    public static void reset() {
        iterations = 0;
    }

    public static long getIterations() {
        return iterations;
    }
    public ArukoneBoard board;
    public final int sleep;

    public ArukoneSolver(ArukoneBoard board, int sleep) {
        this.board = board;
        this.sleep = sleep;
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

    public boolean solve(int pathId) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (board.isSolved()) {
            return true; // board solved
        } else {
            iterations++;
            if (board.getPathById(pathId).isComplete()) {
                return solve(pathId + 1); // if current path is complete, solve the next path
            } else { // if current path is not complete, try to complete it
                boolean next = false;
                int checked = 0;
                Path currentPath = board.getPathById(pathId);
                Point point = currentPath.getLastSetWaypoint();
                Point up = point.up();
                Point right = point.right();
                Point down = point.down();
                Point left = point.left();

                if (board.isFree(up)) {
                    checked++;
                    board.addWaypointByPathId(pathId, up);
                    next = solve(pathId);
                }
                if (!next && board.isFree(right)) {
                    checked++;
                    board.addWaypointByPathId(pathId, right);
                    next = solve(pathId);
                }
                if (!next && board.isFree(down)) {
                    checked++;
                    board.addWaypointByPathId(pathId, down);
                    next = solve(pathId);
                }
                if (!next && board.isFree(left)) {
                    checked++;
                    board.addWaypointByPathId(pathId, left);
                    next = solve(pathId);
                }
                if (board.getFreeNeighbours(point).size() - checked == 0) {
                    board.removeLastSetWaypoint();
                    return false;
                } else {
                    return true;
                }
                // wenn keine moeglichen wege frei
                //      gehe schritt zurueck
                // sonst
                // return true, weil loesung gefunden
            }
        }
    }

    @Override
    public void run() {
        System.out.println(" -- thread started");
        solve(1);
    }
}
