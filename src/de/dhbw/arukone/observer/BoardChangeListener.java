package de.dhbw.arukone.observer;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.gui.controller.SolverController;

/**
 * created by Karsten Köhler on 27.02.2016
 */
public class BoardChangeListener implements ChangeListener {
    private ArukoneBoard board;
    private SolverController controller;

    public BoardChangeListener(ArukoneBoard board, SolverController controller) {
        this.board = board;
        this.controller = controller;
    }

    @Override
    public void boardChanged () {
        controller.drawBoard();
        System.out.println("-- redraw board");
    }
}
