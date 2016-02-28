package de.dhbw.arukone.interfaces;

import de.dhbw.arukone.old.Point;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public interface PointInterface {
    int getX ();
    int getY ();
    boolean isReachable (Point other);
    Point left ();
    Point right ();
    Point down ();
    Point up ();
}