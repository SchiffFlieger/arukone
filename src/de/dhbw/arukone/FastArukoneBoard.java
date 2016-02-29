package de.dhbw.arukone;

import java.util.*;

public class FastArukoneBoard {
    private final int size;
    private final String identifier;

    private final Cell[][] board;
    private final Stack<Cell> stack;
    private final Map<Integer, Cell> startCellMap;
    private final Map<Integer, Cell> endCellMap;

    public FastArukoneBoard (String identifier, int size) {
        this.size = size;
        this.identifier = identifier;

        this.board = new Cell[size][size];
        this.stack = new Stack<>();
        this.startCellMap = new HashMap<>();
        this.endCellMap = new HashMap<>();

        initBoard();
    }

    public void addFixedPath (int value, int startX, int startY, int endX, int endY) {
        Cell start = board[startX][startY];
        Cell end = board[endX][endY];

        start.setValue(value);
        start.setFixed();

        end.setValue(value);
        end.setFixed();

        startCellMap.put(value, start);
        endCellMap.put(value, end);
    }

    public boolean isFree (Cell cell) {
        return cell != null && cell.getValue() == 0;
    }

    public boolean isPathComplete (int pathId) {
        Cell start = startCellMap.get(pathId);
        return isPathComplete(start);
    }

    public boolean isSolved () {
        for (int key : startCellMap.keySet()) {
            if (!isPathComplete(key)) {
                return false;
            }
        }
        return true;
    }

    public void setWaypoint (Cell active, Cell next) {
        int x = next.getX();
        int y = next.getY();

        active.setNext(next);
        next.setPrevious(active);

        next.setValue(active.getValue());
        stack.add(next);
    }

    public void removeLastSetWaypoint () {
        if (!stack.isEmpty()) {
            Cell deleted = stack.pop();
            deleted.setValue(0); // clear cell

            deleted.getPrevious().setNext(null);
            deleted.setPrevious(null);
        }
    }

    public Cell left (Cell active) {
        if (active.getY() <= 0)
            return null;
        return board[active.getX()][active.getY() - 1];
    }

    public Cell right (Cell active) {
        if (active.getY() >= size - 1)
            return null;
        return board[active.getX()][active.getY() + 1];
    }

    public Cell up (Cell active) {
        if (active.getX() <= 0) {
            return null;
        }
        return board[active.getX() - 1][active.getY()];
    }

    public Cell down (Cell active) {
        if (active.getX() >= size - 1) {
            return null;
        }
        return board[active.getX() + 1][active.getY()];
    }

    public List<Cell> getFreeNeighbours (Cell cell) {
        List<Cell> neighbours = new ArrayList<>(4);

        Cell up = this.up(cell);
        Cell right = this.right(cell);
        Cell down = this.down(cell);
        Cell left = this.left(cell);

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

    public Cell getActivePoint (int pathId) {
        if (!stack.isEmpty() && stack.peek().getValue() == pathId) {
            return stack.peek();
        }
        return startCellMap.get(pathId);
    }

    public String getIdentifier () {
        return identifier;
    }

    public int getSize () {
        return this.size;
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

    private boolean isPathComplete (Cell active) {
        Cell next = active.getNext();
        if (next != null) {
            return isPathComplete(next);
        } else {
            return active.isConnected(endCellMap.get(active.getValue()));
        }
    }

    private String getLineSeparator () {
        StringBuilder lineSepBuilder = new StringBuilder();
        for (int i = 0; i <= this.size; i++) {
            lineSepBuilder.append(".   ");
        }
        return lineSepBuilder.toString() + "\n";
    }

    private void initBoard () {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = new Cell(x, y);
            }
        }
    }
}
