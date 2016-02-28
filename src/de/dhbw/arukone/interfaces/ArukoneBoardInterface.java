package de.dhbw.arukone.interfaces;

import de.dhbw.arukone.old.Path;
import de.dhbw.arukone.old.Point;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public interface ArukoneBoardInterface {
    void addPath (Path path);

    Path getPathById (int id);

    void addWaypointByPathId (int id, Point point);

    boolean isFree (Point point);

    boolean isSolved ();

    String deepToString ();

    int getSize ();

    List<Point> getFreeNeighbours (Point point);

    void removeLastSetWaypoint ();
}
