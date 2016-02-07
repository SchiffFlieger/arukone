package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public class ArukoneBoard {
    private ArrayList<Path> paths;
    private boolean[][] occupiedFields;
    private final int size;

    public ArukoneBoard(final int size) {
        this.size = size;
        this.occupiedFields = new boolean[size][size];
        this.paths = new ArrayList<>();
    }

    public void addPath(Path path) {
        this.paths.add(path);
        occupy(path.getStart());
        occupy(path.getEnd());
        for (Point point : path.getPath()) {
            occupy(point);
        }
    }

    private void occupy(Point point) {
        this.occupiedFields[point.x][point.y] = true;
    }

    private void free(Point point) {
        this.occupiedFields[point.x][point.y] = false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String lineSep = getLineSeparator();
        result.append(lineSep);

        int[][] grid = getGrid();
        return Arrays.deepToString(grid);


//        return result.toString();

    }

    private int[][] getGrid() {
        int[][] grid = new int[this.size][this.size];
        for (Path path : this.paths) {
            grid[path.getStart().x][path.getStart().y] = path.getId();
            grid[path.getEnd().x][path.getEnd().y] = path.getId();
            for (Point point : path.getPath()) {
                grid[point.x][point.y] = path.getId();
            }
        }
        return grid;
    }

    private String getLineSeparator() {
        StringBuilder lineSepBuilder = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            lineSepBuilder.append(".   ");
        }
        return lineSepBuilder.toString() + "\n";
    }
}


















