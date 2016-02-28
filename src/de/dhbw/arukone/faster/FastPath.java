package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PathInterface;
import de.dhbw.arukone.old.Point;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class FastPath implements PathInterface {
    @Override
    public int getId () {
        return 0;
    }

    @Override
    public Point getLastPointFromStart () {
        return null;
    }

    @Override
    public Point getLastPointFromEnd () {
        return null;
    }

    @Override
    public Point getStart () {
        return null;
    }

    @Override
    public Point getEnd () {
        return null;
    }

    @Override
    public boolean isComplete () {
        return false;
    }

    @Override
    public boolean addWaypoint (Point point) {
        return false;
    }

    @Override
    public Point getLastSetWaypoint () {
        return null;
    }

    @Override
    public Point removeLastSetWaypoint () {
        return null;
    }

    @Override
    public List<Point> getAllPoints () {
        return null;
    }
}
