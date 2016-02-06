package de.dhbw.arukone;

import java.util.Arrays;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public class ArukoneBoard {
    private boolean[][] forbiddenMove;
    private int[][] board;
    private int[][] originalBoard;

    public ArukoneBoard() {

        if (Configuration.instance.isDifficult) {
            initDifficult();
        } else {
            initEasy();
        }

        this.board = originalBoard;
        setForbiddenMoves();
    }

    public void setValueAt(int x, int y, int value) {
        this.board[x][y] = value;
    }

    public int getValueAt(int x, int y) {
        return this.board[x][y];
    }

    public int getEdgeLength() {
        return this.board.length;
    }

    private void setForbiddenMoves () {
        int size = this.originalBoard.length;
        this.forbiddenMove = new boolean[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (this.originalBoard[x][y] == 0) {
                    this.forbiddenMove[x][y] = false;
                } else {
                    this.forbiddenMove[x][y] = true;
                }
            }
        }
    }

    private void initDifficult () {
        this.originalBoard = new int[][] {
                {0,0,0,0,0,0,1,2,3},
                {0,4,0,0,0,0,0,0,0},
                {5,0,0,1,0,0,0,0,0},
                {0,0,0,2,0,0,0,0,0},
                {0,3,0,0,0,0,0,7,0},
                {0,0,0,0,9,0,0,0,0},
                {0,0,0,0,0,0,8,0,8},
                {0,0,0,5,0,0,9,7,6},
                {4,0,0,0,0,6,0,0,0}
        };
    }

    private void initEasy () {
        this.originalBoard = new int[][] {
                {1,0,2,0,3},
                {0,0,4,0,5},
                {0,0,0,0,0},
                {0,2,0,3,0},
                {0,1,4,5,0}
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                String format = "%3d";
                result.append(String.format(format, this.board[x][y]));
            }
            result.append("\n");
        }

        return result.toString();
    }

    public String advancedToString() {
        StringBuilder result = new StringBuilder();

        StringBuilder lineSeparatorBuilder = new StringBuilder();
        for (int i = 0; i < this.board.length; i++) {
            lineSeparatorBuilder.append(".   ");
        }
        lineSeparatorBuilder.append(".\n");
        lineSeparatorBuilder.insert(0, "\n");
        String lineSeparator = lineSeparatorBuilder.toString();

        result.append(lineSeparator);
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                String format = "%3d ";
                result.append(String.format(format, this.board[x][y]));
            }
            result.append(lineSeparator);
        }

        return  result.toString();
    }
}


















