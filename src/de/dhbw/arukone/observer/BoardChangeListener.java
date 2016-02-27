package de.dhbw.arukone.observer;

import de.dhbw.arukone.ArukoneBoard;

/**
 * created by Karsten Köhler on 27.02.2016
 */
public class BoardChangeListener implements ChangeListener {
    private ArukoneBoard board;

    public BoardChangeListener(ArukoneBoard board) {
        this.board = board;
    }

    @Override
    public void boardChanged () {
        System.out.println("-----------------------");
        System.out.println(board.deepToString());
    }
}
