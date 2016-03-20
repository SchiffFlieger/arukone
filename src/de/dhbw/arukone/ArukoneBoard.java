package de.dhbw.arukone;

import java.util.*;

public class ArukoneBoard {
    private final int size;
    private final String identifier;

    /**
     * represents the current state of each cell
     */
    private final Cell[][] board;

    /**
     * saves order of changes
     */
    private final Stack<Cell> stack;

    /**
     * contains all start cells of the board
     */
    private final Map<Integer, Cell> startCellMap;

    /**
     * contains all end cells of the board
     */
    private final Map<Integer, Cell> endCellMap;

    /**
     * Basic constructor for {@link ArukoneBoard}.
     * @param identifier identifier for the board
     * @param size edge length
     */
    public ArukoneBoard (String identifier, int size) {
        this.size = size;
        this.identifier = identifier;

        this.board = new Cell[size][size];
        this.stack = new Stack<>();
        this.startCellMap = new HashMap<>();
        this.endCellMap = new HashMap<>();

        initBoard();
    }

    /**
     * Accessor for identifier.
     * @return identifier
     */
    public String getIdentifier () {
        return identifier;
    }

    /**
     * Accessor for edge length
     * @return edge length
     */
    public int getSize () {
        return this.size;
    }

    /**
     * Adds a new path to the board. A path contains a start and end point.
     * @param value id of the path
     * @param startX x-coordinate of start point
     * @param startY y-coordinate of start point
     * @param endX x-coordinate of end point
     * @param endY y-coordinate of end point
     */
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

    /**
     * Checks if the given cell is currently used by any path.
     * @param cell cell to check
     * @return true if cell is free, else false
     */
    public boolean isFree (Cell cell) {
        return cell != null && cell.getValue() == 0;
    }

    /**
     * Checks if a given path is complete. A path is complete if there is a chain of cells with the specific path id leading from path start point to path end point.
     * @param pathId path to check
     * @return true if path is complete, else false
     */
    public boolean isPathComplete (int pathId) {
        Cell start = startCellMap.get(pathId);
        return isPathComplete(start);
    }

    /** Checks if the board is solved. The board is solved if each path is complete.
     * @return true if solved, else false
     */
    public boolean isSolved () {
        for (int key : startCellMap.keySet()) {
            if (!isPathComplete(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Connects two adjacent cells to create a path. The target cell receives the path id from the source cell.
     * @param active source cell
     * @param next target cell
     */
    public void setWaypoint (Cell active, Cell next) {
        int x = next.getX();
        int y = next.getY();

        active.setNext(next);
        next.setPrevious(active);

        next.setValue(active.getValue());
        stack.add(next);
    }

    /**
     * Removes last set connection between two cells.
     */
    public void removeLastSetWaypoint () {
        if (!stack.isEmpty()) {
            Cell deleted = stack.pop();
            deleted.setValue(0); // clear cell

            deleted.getPrevious().setNext(null);
            deleted.setPrevious(null);
        }
    }

    /**
     * Returns the cell on the left side of the given cell. Returns null if there is no cell to the left.
     * @param active current cell
     * @return cell to the left
     */
    public Cell left (Cell active) {
        if (active.getY() <= 0)
            return null;
        return board[active.getX()][active.getY() - 1];
    }

    /**
     * Returns the cell on the right side of the given cell. Returns null if there is no cell to the right.
     * @param active current cell
     * @return cell to the right
     */
    public Cell right (Cell active) {
        if (active.getY() >= size - 1)
            return null;
        return board[active.getX()][active.getY() + 1];
    }

    /**
     * Returns the cell above the given cell. Returns null if there is no cell above.
     * @param active current cell
     * @return cell above
     */
    public Cell up (Cell active) {
        if (active.getX() <= 0) {
            return null;
        }
        return board[active.getX() - 1][active.getY()];
    }

    /**
     * Returns the cell below the given cell. Returns null if there is no cell below.
     * @param active current cell
     * @return cell below
     */
    public Cell down (Cell active) {
        if (active.getX() >= size - 1) {
            return null;
        }
        return board[active.getX() + 1][active.getY()];
    }

    /**
     * Returns a list of adjacent cells.
     * @param cell current cell
     * @return neighbour cells
     */
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

    /**
     * Returns last connected cell of the given path id. If there are no connected cells in that path, the start point of the path is returned.
     * @param pathId path
     * @return last connected cell
     */
    public Cell getActivePoint (int pathId) {
        if (!stack.isEmpty() && stack.peek().getValue() == pathId) {
            return stack.peek();
        }
        return startCellMap.get(pathId);
    }

    @Override
    /**
     * Creates a string representation of the board.
     * @see Object#toString()
     */
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

    /**
     * Checks if the given path is complete.
     * @param active current cell
     * @return true if complete, else false
     * @see ArukoneBoard#isPathComplete(int)
     */
    private boolean isPathComplete (Cell active) {
        Cell next = active.getNext();
        if (next != null) {
            return isPathComplete(next);
        } else {
            return active.isConnected(endCellMap.get(active.getValue()));
        }
    }

    /**
     * Creates a line with dots for better visualization in {@link ArukoneBoard#toString()}.
     * @return separator line
     * @see ArukoneBoard#toString()
     */
    private String getLineSeparator () {
        StringBuilder lineSepBuilder = new StringBuilder();
        for (int i = 0; i <= this.size; i++) {
            lineSepBuilder.append(".   ");
        }
        return lineSepBuilder.toString() + "\n";
    }

    /**
     * Creates each cell of the board and marks them as empty.
     */
    private void initBoard () {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = new Cell(x, y);
            }
        }
    }
}
