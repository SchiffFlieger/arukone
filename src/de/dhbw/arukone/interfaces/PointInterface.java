package de.dhbw.arukone.interfaces;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public interface PointInterface {
    int getX ();
    int getY ();
    boolean isNeighbour (PointInterface other);
    PointInterface left ();
    PointInterface right ();
    PointInterface down ();
    PointInterface up ();
}