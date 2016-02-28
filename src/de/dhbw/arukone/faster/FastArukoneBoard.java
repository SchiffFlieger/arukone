package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.ArukoneBoardInterface;
import de.dhbw.arukone.old.Path;
import de.dhbw.arukone.old.Point;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class FastArukoneBoard implements ArukoneBoardInterface {
    @Override
    public void addPath (Path path) {

    }

    @Override
    public Path getPathById (int id) {
        return null;
    }

    @Override
    public void addWaypointByPathId (int id, Point point) {

    }

    @Override
    public boolean isFree (Point point) {
        return false;
    }

    @Override
    public boolean isSolved () {
        return false;
    }

    @Override
    public String deepToString () {
        return null;
    }

    @Override
    public int getSize () {
        return 0;
    }

    @Override
    public List<Point> getFreeNeighbours (Point point) {
        return null;
    }

    @Override
    public void removeLastSetWaypoint () {

    }
}
