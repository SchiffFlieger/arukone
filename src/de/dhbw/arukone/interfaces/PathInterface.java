package de.dhbw.arukone.interfaces;

import de.dhbw.arukone.old.Point;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public interface PathInterface {
    int getId ();

    Point getLastPointFromStart ();

    Point getLastPointFromEnd ();

    Point getStart ();

    Point getEnd ();

    boolean isComplete ();

    boolean addWaypoint (Point point);

    Point getLastSetWaypoint ();

    Point removeLastSetWaypoint ();

    List<Point> getAllPoints ();
}
