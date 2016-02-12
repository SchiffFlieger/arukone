package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.List;

public class ArukoneSolver {

    public ArukoneSolver() {
    }

    public ArukoneBoard solve(ArukoneBoard board) {
        if (board.isSolved()) {
            return board; // board solved
        }
        return solve(board);
    }

    public ArukoneBoard solvePath(ArukoneBoard currentBoard, int pathId) {
        if (currentBoard.getPathById(pathId).isComplete()) {
            return currentBoard; // path complete
        } else {
            ArukoneBoard newBoard = new ArukoneBoard(currentBoard);
            Path currentPath = newBoard.getPathById(pathId);
            Point currentPoint = currentPath.getLastSetWaypoint();
            List<Point> directions = new ArrayList<>(4);
            directions.add(currentPoint.up());
            directions.add(currentPoint.right());
            directions.add(currentPoint.down());
            directions.add(currentPoint.left());

            int count = 0;
            for (Point point : directions){
                if (newBoard.isFree(point)) {
                    newBoard.addWaypointByPathId(pathId, point);
//                  hier den illegalen Move eintragen, Unterscheidung nach Richtung wird noch gebraucht
                    return solvePath(newBoard, pathId);
                } else {
                    count++;
                }
                if (count >= 4) {
                    System.out.println("in einer Sackgasse:");
                    System.out.println(newBoard);
                    newBoard.removeLastSetWaypointByPathId(pathId);
                    return solvePath(newBoard, pathId);
                }
            }
        }
        return null;
    }
}
