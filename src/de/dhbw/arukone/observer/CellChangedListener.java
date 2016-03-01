package de.dhbw.arukone.observer;

import de.dhbw.arukone.Cell;

/**
 * created by Karsten Köhler on 01.03.2016
 */
public class CellChangedListener implements ChangeListener {
    @Override
    public void changed (Cell cell ) {
        System.out.println("(" + cell + ") changed");
    }
}
