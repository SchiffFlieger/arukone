package de.dhbw.arukone.faster;

import de.dhbw.arukone.interfaces.PointInterface;
import de.dhbw.arukone.old.Point;

/**
 * created by Karsten Köhler on 28.02.2016
 */
public class FastPoint implements PointInterface {
    @Override
    public int getX () {
        return 0;
    }

    @Override
    public int getY () {
        return 0;
    }

    @Override
    public boolean isReachable (Point other) {
        return false;
    }

    @Override
    public Point left () {
        return null;
    }

    @Override
    public Point right () {
        return null;
    }

    @Override
    public Point down () {
        return null;
    }

    @Override
    public Point up () {
        return null;
    }
}
