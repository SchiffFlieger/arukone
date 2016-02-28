package de.dhbw.arukone.old;


import de.dhbw.arukone.interfaces.ArukoneBoardInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArukoneBoard implements ArukoneBoardInterface {
    private final int size;

    private final List<Path> paths;
    private final Stack<Integer> pathStack;
    private final boolean[][] occupiedFields;
    private final String identifier;

    public ArukoneBoard (String identifier, final int size) {
        this.size = size;
        this.occupiedFields = new boolean[size][size];
        this.paths = new ArrayList<>();
        this.pathStack = new Stack<>();
        this.identifier = identifier;
    }

//    public List<Path> getPaths () {
//        return paths;
//    }

    @Override
    public void addPath (Path path) {
        this.paths.add(path);
        path.getAllPoints().forEach(this::occupy);
    }

    @Override
    public Path getPathById (int id) {
        return this.paths.get(id - 1);
    }

    @Override
    public void addWaypointByPathId (final int id, Point point) {
        this.paths.get(id - 1).addWaypoint(point);
        pathStack.push(id - 1);
        occupy(point);

    }

    @Override
    public boolean isFree (Point point) {
        return (point != null && !this.occupiedFields[point.getX()][point.getY()]);
    }

    @Override
    public boolean isSolved () {
        for (Path path : paths) {
            if (!path.isComplete()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString () {
        return this.identifier;
    }

    @Override
    public String deepToString () {
        StringBuilder result = new StringBuilder();
        String lineSep = getLineSeparator();

        result.append(lineSep);
        int[][] grid = getGrid();
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (grid[x][y] != 0) {
                    result.append(String.format("%3d ", grid[x][y]));
                } else {
                    result.append("    ");
                }
            }
            result.append("\n").append(lineSep);
        }

        return result.toString();
    }

    @Override
    public int getSize () {
        return size;
    }

    @Override
    public List<Point> getFreeNeighbours (Point point) {
        List<Point> neighbours = new ArrayList<>(4);

        Point up = point.up();
        Point right = point.right();
        Point down = point.down();
        Point left = point.left();

        if (up != null && this.isFree(up)) {
            neighbours.add(up);
        }
        if (right != null && this.isFree(right)) {
            neighbours.add(right);
        }
        if (down != null && this.isFree(down)) {
            neighbours.add(down);
        }
        if (left != null && this.isFree(left)) {
            neighbours.add(left);
        }

        return neighbours;
    }

    @Override
    public void removeLastSetWaypoint () {
        removeLastSetWaypointByPathId(pathStack.peek() + 1);
    }

    private void removeLastSetWaypointByPathId (int id) {
        Point point = paths.get(id - 1).removeLastSetWaypoint();
        if (point != null) {
            pathStack.pop();
            free(point);
        }
    }

    private void free (Point point) {
        this.occupiedFields[point.getX()][point.getY()] = false;
    }

    private void occupy (Point point) {
        this.occupiedFields[point.getX()][point.getY()] = true;
    }

    private int[][] getGrid () {
        int[][] grid = new int[this.size][this.size];
        for (Path path : this.paths) {
            grid[path.getStart().getX()][path.getStart().getY()] = path.getId();
            grid[path.getEnd().getX()][path.getEnd().getY()] = path.getId();
            for (Point point : path.getAllPoints()) {
                grid[point.getX()][point.getY()] = path.getId();
            }
        }
        return grid;
    }

    private String getLineSeparator () {
        StringBuilder lineSepBuilder = new StringBuilder();
        for (int i = 0; i <= this.size; i++) {
            lineSepBuilder.append("   ");
        }
        return lineSepBuilder.toString() + "\n";
    }
}


















