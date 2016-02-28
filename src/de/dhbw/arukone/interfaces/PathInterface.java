package de.dhbw.arukone.interfaces;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public interface PathInterface {
    int getId ();

    PointInterface getLastPointFromStart ();

    PointInterface getLastPointFromEnd ();

    PointInterface getStart ();

    PointInterface getEnd ();

    boolean isComplete ();

    boolean addWaypoint (PointInterface point);

    PointInterface getLastSetWaypoint ();

    PointInterface removeLastSetWaypoint ();

    List<PointInterface> getAllPoints ();
}
