package de.dhbw.arukone.observer;

import de.dhbw.arukone.Cell;
import de.dhbw.arukone.FastArukoneBoard;

/**
 * created by Karsten Köhler on 01.03.2016
 */
public class CellChangedListener implements ChangeListener {
    private FastArukoneBoard board;

    public CellChangedListener (FastArukoneBoard board) {
        this.board = board;
    }

    @Override
    public void changed (Cell cell ) {
        System.out.println("(" + cell + ") changed");
    }
}
