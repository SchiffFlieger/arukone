package de.dhbw.arukone;

import java.util.List;

public class ArukoneSolver {

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
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(board);
//        System.out.println("------------------------");
        if (board.isSolved()) {
            return board; // board solved
        } else
        {
            if (board.getPathById(pathId).isComplete()) {
                return solve(new ArukoneBoard(board), pathId+1); // if current path is complete, solve the next path
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
