package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PointInterface;

import java.util.*;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class FastArukoneBoard {
    private final int size;
    private final String identifier;

    private final Cell[][] board;
    private final Stack<Cell> stack;
    private final Map<Integer, Cell> cellMap;

    public FastArukoneBoard(String identifier, int size) {
        this.size = size;
        this.identifier = identifier;

        this.board = new Cell[size][size];
        this.stack = new Stack<>();
        this.cellMap = new HashMap<>();

        initBoard();
    }

    public void addFixedPath(int value, int startX, int startY, int endX, int endY) {
        Cell start = board[startX][startY];
        Cell end = board[endX][endY];

        start.setValue(value);
        start.setFixed();

        end.setValue(value);
        end.setFixed();

        cellMap.put(value, start);
    }

    public boolean isPathComplete (int pathId) {
        Cell start = cellMap.get(pathId);
        return false;
    }

    private boolean isConnected(Cell current, Cell next) {
        if (current == null) { // erste Zelle
            current = next;
            List<Cell> neighbours = getFreeNeighbours(current);
            if (neighbours.isEmpty()) {
                return false; // keine Zelle mit der Startzelle verbunden
            }
            for (Cell cell : neighbours) {
                if (cell.getValue() == current.getValue()) {
                    next = cell;
                }
            }
            return isConnected(current, next);
        } else {
            if (next.isFixed()) {
                return true;
            }
        }
        // TODO implement
        return false;
    }

    public Cell getActivePoint() {
        return stack.peek();
    }

    public void setWaypoint (int x, int y, int value) {
        board[x][y].setValue(value);
        stack.add(board[x][y]);
    }

    public boolean isFree (Cell cell) {
        return cell.getValue() == 0;
    }

    public boolean isSolved () {
        //TODO implement
        return false;
    }

    public int getSize () {
        return this.size;
    }

    public List<Cell> getFreeNeighbours (Cell point) {
        List<Cell> neighbours = new ArrayList<>(4);

        Cell up = point.up();
        Cell right = point.right();
        Cell down = point.down();
        Cell left = point.left();

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

    public void removeLastSetWaypoint () {
        if (!stack.isEmpty()) {
            Cell point = stack.pop();
            board[point.getX()][point.getY()].setValue(0); // clear cell
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        String lineSep = getLineSeparator();

        result.append(lineSep);
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (board[x][y].getValue() != 0) {
                    result.append(String.format("%3d ", board[x][y].getValue()));
                } else {
                    result.append("    ");
                }
            }
            result.append("\n").append(lineSep);
        }

        return result.toString();
    }

    private String getLineSeparator () {
        StringBuilder lineSepBuilder = new StringBuilder();
        for (int i = 0; i <= this.size; i++) {
            lineSepBuilder.append(".   ");
        }
        return lineSepBuilder.toString() + "\n";
    }

    private void initBoard() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = new Cell(x, y, size);
            }
        }
    }
}
