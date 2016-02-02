package de.dhbw.sudoku;

import java.util.ArrayList;

public class SudokuBoard {
    private ArrayList<ArrayList<Integer>> numberPool;
    private boolean[][] forbiddenMove;
    private int[][] board;
    private int[][] originalBoard;

    public SudokuBoard(boolean isPreFilled) {
        numberPool = new ArrayList<>();
        forbiddenMove = new boolean[9][9];

        for (int i = 0;i < 9;i++) {
            numberPool.add(new ArrayList<>());
            for (int j = 1;j < 10;j++)
                numberPool.get(i).add(j);
        }

        if (isPreFilled) {
            if (Configuration.instance.isSudokuDifficult)
                defaultBoard9x9();
            else
                defaultBoard9x9Simple();
        } else
            board = new int[9][9];
    }

    public SudokuBoard(SudokuBoard sudokuBoard) {
        board = new int[9][9];
        originalBoard = sudokuBoard.getOriginalBoard();
        copySudokuBoard(sudokuBoard);
    }

    public void copySudokuBoard(SudokuBoard sudokuBoard) {
        int[][] data = sudokuBoard.getBoard();

        for (int i = 0;i < data.length;i++)
            for (int j = 0;j < data[i].length;j++)
                board[i][j] = data[i][j];

        forbiddenMove = sudokuBoard.getForbiddenMove();
    }

    private void defaultBoard9x9Simple() {
        board = new int[][]
                {{9,0,6,7,4,1,8,0,2},
                        {4,5,0,9,0,8,0,6,3},
                        {8,7,2,0,6,3,9,4,0},
                        {0,1,4,2,8,0,6,0,9},
                        {2,0,5,3,0,7,4,1,0},
                        {0,8,9,0,1,6,0,2,5},
                        {1,2,0,6,3,0,0,9,7},
                        {5,4,0,1,0,9,2,0,6},
                        {0,9,7,8,0,2,1,3,0}};

        originalBoard = board;

        for (int i = 0;i < board.length;i++)
            for (int j = 0;j < board[i].length;j++)
                if (board[i][j] != 0) {
                    forbiddenMove[i][j] = true;
                    numberPool.get(j).remove(new Integer(board[i][j]));
                }

        fillEmptyFields();
    }

    // solution
    // 5 3 4 8 7 6 9 1 2
    // 6 7 2 1 9 5 3 4 8
    // 8 5 9 7 6 1 4 2 3
    // 4 2 6 3 5 8 7 9 1
    // 7 1 3 9 2 4 8 5 6
    // 9 6 1 5 3 7 2 8 4
    // 2 8 7 4 1 9 6 3 5
    // 3 4 5 6 8 2 1 7 9

    private void defaultBoard9x9() {
        board = new int[][]
                {{5,3,0,0,7,0,0,0,0},
                        {6,0,0,1,9,5,0,0,0},
                        {0,9,8,0,0,0,0,6,0},
                        {8,0,0,0,6,0,0,0,3},
                        {4,0,0,8,0,3,0,0,1},
                        {7,0,0,0,2,0,0,0,6},
                        {0,6,0,0,0,0,2,8,0},
                        {0,0,0,4,1,9,0,0,5},
                        {0,0,0,0,8,0,0,7,9}};

        originalBoard = board;

        for (int i = 0;i < board.length;i++)
            for (int j = 0;j < board[i].length;j++)
                if (board[i][j] != 0) {
                    forbiddenMove[i][j] = true;
                    numberPool.get(j).remove(new Integer(board[i][j]));
                }

        fillEmptyFields();
    }

    public void fillEmptyFields() {
        for (int i = 0;i < board.length;i++)
            for (int j = 0;j < board[i].length;j++)
                if (board[i][j] == 0)
                    board[i][j] = numberPool.get(j).remove(Configuration.instance.randomGenerator.nextInt(numberPool.get(j).size()));
    }

    public boolean swap(int column,int row1,int row2) {
        int temp;

        if (isForbidden(row1,column))
            return false;

        if (isForbidden(row2,column))
            return false;

        temp = board[row1][column];
        board[row1][column] = board[row2][column];
        board[row2][column] = temp;

        return true;
    }

    public boolean isForbidden(int row,int column) {
        return forbiddenMove[row][column];
    }

    public int[][] getBoard() {
        return board;
    }

    public int[][] getOriginalBoard() {
        return originalBoard;
    }

    public void setValueAt(int row,int column,int value) {
        board[row][column] = value;
    }

    public int getValueAt(int row,int column) {
        return board[row][column];
    }

    public boolean[][] getForbiddenMove() {
        return forbiddenMove;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0;i < board.length;i++) {
            if (i == 3 || i == 6)
                stringBuilder.append("-------------------\n");

            for (int j = 0;j < board[i].length;j++) {
                if (j == 3 || j == 6)
                    stringBuilder.append('|');

                if (board[i][j] > 0)
                    stringBuilder.append(board[i][j]);
                else
                    stringBuilder.append('_');

                stringBuilder.append(' ');
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}