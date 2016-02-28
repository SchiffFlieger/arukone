package de.dhbw.arukone.interfaces;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public interface ArukoneBoardInterface {
    void addPath (PathInterface path);

    PathInterface getPathById (int id);

    void addWaypointByPathId (int id, PointInterface point);

    boolean isFree (PointInterface point);

    boolean isSolved ();

    String deepToString ();

    int getSize ();

    List<PointInterface> getFreeNeighbours (PointInterface point);

    void removeLastSetWaypoint ();
}
