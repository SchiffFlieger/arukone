package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.ArukoneBoardInterface;
import de.dhbw.arukone.interfaces.PathInterface;
import de.dhbw.arukone.interfaces.PointInterface;
import de.dhbw.arukone.old.Path;

import java.util.List;
import java.util.Stack;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class FastArukoneBoard implements ArukoneBoardInterface {
    private final int size;
    private final String identifier;

    private final Cell[][] board;
    private final Stack<Cell> stack;

    public FastArukoneBoard(String identifier, int size) {
        this.size = size;
        this.identifier = identifier;

        this.board = new Cell[size][size];
        this.stack = new Stack<>();
    }

    @Override
    public void addPath (PathInterface path) {
        //TODO implement
    }

    @Override
    public Path getPathById (int id) {
        //TODO implement
        return null;
    }

    @Override
    public void addWaypointByPathId (int id, PointInterface point) {
        //TODO implement
    }

    @Override
    public boolean isFree (PointInterface point) {
        return ((Cell) point).getValue() == 0;
    }

    @Override
    public boolean isSolved () {
        //TODO implement
        return false;
    }

    @Override
    public int getSize () {
        return this.size;
    }

    @Override
    public List<PointInterface> getFreeNeighbours (PointInterface point) {
        //TODO implement
        return null;
    }

    @Override
    public void removeLastSetWaypoint () {
        //TODO implement
    }

    @Override
    public String deepToString () {
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
}
