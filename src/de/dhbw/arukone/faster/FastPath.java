package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PathInterface;
import de.dhbw.arukone.interfaces.PointInterface;
import de.dhbw.arukone.old.Point;

import java.util.List;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class FastPath implements PathInterface {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public PointInterface getLastPointFromStart() {
        return null;
    }

    @Override
    public PointInterface getLastPointFromEnd() {
        return null;
    }

    @Override
    public PointInterface getStart() {
        return null;
    }

    @Override
    public PointInterface getEnd() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean addWaypoint(PointInterface point) {
        return false;
    }

    @Override
    public PointInterface getLastSetWaypoint() {
        return null;
    }

    @Override
    public PointInterface removeLastSetWaypoint() {
        return null;
    }

    @Override
    public List<PointInterface> getAllPoints() {
        return null;
    }
}
