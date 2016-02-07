package de.dhbw.arukone;

import java.util.ArrayList;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public class ArukoneBoard {
    private final int size;
    private ArrayList<Path> paths;
    private boolean[][] occupiedFields;

    public ArukoneBoard(final int size) {
        this.size = size;
        this.occupiedFields = new boolean[size][size];
        this.paths = new ArrayList<>();
    }

    public void addPath(Path path) {
        this.paths.add(path);
        for (Point point : path.getAllPoints()) {
            occupy(point);
        }
    }

    private void occupy(Point point) {
        this.occupiedFields[point.getX()][point.getY()] = true;
    }

    private void free(Point point) {
        this.occupiedFields[point.getX()][point.getY()] = false;
    }

    @Override
    public String toString() {
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
            result.append("\n" + lineSep);
        }

        return result.toString();
    }

    private int[][] getGrid() {
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

    private String getLineSeparator() {
        StringBuilder lineSepBuilder = new StringBuilder();
        for (int i = 0; i <= this.size; i++) {
            lineSepBuilder.append(".   ");
        }
        return lineSepBuilder.toString() + "\n";
    }
}


















