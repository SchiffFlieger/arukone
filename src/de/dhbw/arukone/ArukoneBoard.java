package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArukoneBoard {
    private final int size;

    private List<Path> paths;
    private boolean[][] occupiedFields;
    private final String identifier;
    public ArukoneBoard(String identifier, final int size) {
        this.size = size;
        this.occupiedFields = new boolean[size][size];
        this.paths = new ArrayList<>();
        this.identifier = identifier;
    }

    public ArukoneBoard(ArukoneBoard board) {
        this.identifier = board.identifier;
        this.size = board.size;
        this.occupiedFields = new boolean[size][size];
        for (int x = 0; x < size; x++) {
            System.arraycopy(board.occupiedFields[x], 0, this.occupiedFields[x], 0, size);
        }
        this.paths = new ArrayList<>();
        this.paths.addAll(board.paths.stream().map(Path::new).collect(Collectors.toList()));
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void addPath(Path path) {
        this.paths.add(path);
        path.getAllPoints().forEach(this::occupy);
    }

    public Path getPathById(int id) {
        return this.paths.get(id - 1);
    }

    public boolean addWaypointByPathId(final int id, Point point) {
        if (this.paths.get(id - 1).addWaypoint(point)) {
            occupy(point);
            return true;
        }
        return false;
    }

    private void occupy(Point point) {
        this.occupiedFields[point.getX()][point.getY()] = true;
    }

    public boolean isFree(Point point) {
        return (point != null && !this.occupiedFields[point.getX()][point.getY()]);
    }

    public boolean isSolved() {
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

    public int getSize () {
        return size;
    }

    public List<Point> getFreeNeighbours(Point point) {
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

    public boolean hasFreeNeighbours(Point point) {
        return (getFreeNeighbours(point).size() > 0);
    }

    public boolean isSolveable() {
        for (Path path : paths) {
            if (!hasFreeNeighbours(path.getLastPointFromStart()) || !hasFreeNeighbours(path.getLastPointFromEnd())) {
                return false;
            }
        }
        return true;
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


















