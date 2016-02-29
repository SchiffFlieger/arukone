package de.dhbw.arukone;

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
        //TODO hier ist ne Endlosschleife
        Cell active = cellMap.get(pathId);
        Cell previous = null;

        boolean go = true;

        while(true) {
            Cell up = this.up(active);
            Cell right = this.right(active);
            Cell down = this.down(active);
            Cell left = this.left(active);

            if (active.isConnected(up)) {
                if (!up.equals(previous)) {
                    if (up.isFixed()) {
                        return true;
                    } else {
                        previous = active;
                        active = up;
                        continue;
                    }
                }
            }
            if (active.isConnected(right)) {
                if (!right.equals(previous)) {
                    if (right.isFixed()) {
                        return true;
                    } else {
                        previous = active;
                        active = right;
                        continue;
                    }
                }
            }
            if (active.isConnected(down)) {
                if (!down.equals(previous)) {
                    if (down.isFixed()) {
                        return true;
                    } else {
                        previous = active;
                        active = down;
                        continue;
                    }
                }
            }
            if (active.isConnected(left)) {
                if (!left.equals(previous)) {
                    if (left.isFixed()) {
                        return true;
                    } else {
                        previous = active;
                        active = left;
                        continue;
                    }
                }
            }

            return false;
        }
    }

    public Cell getActivePoint(int pathId) {
        if (!stack.isEmpty() && stack.peek().getValue() == pathId) {
            return stack.peek();
        }
        return cellMap.get(pathId);
    }

    public void setWaypoint (int x, int y, int value) {
        board[x][y].setValue(value);
        stack.add(board[x][y]);
    }

    public boolean isFree (Cell cell) {
        return cell != null && cell.getValue() == 0;
    }

    public boolean isSolved () {
        for (int key : cellMap.keySet()) {
            if (!isPathComplete(key)) {
                return false;
            }
        }
        return true;
    }

    public void removeLastSetWaypoint () {
        if (!stack.isEmpty()) {
            Cell point = stack.pop();
            board[point.getX()][point.getY()].setValue(0); // clear cell
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

    public int getSize () {
        return this.size;
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
                board[x][y] = new Cell(x, y);
            }
        }
    }
}
