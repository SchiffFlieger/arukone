package de.dhbw.arukone.old;

import de.dhbw.arukone.interfaces.PathInterface;
import de.dhbw.arukone.interfaces.PointInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Path implements PathInterface {
    private final int id;
    private final List<PointInterface> pathFromStart;
    private final List<PointInterface> pathFromEnd;
    private final List<Integer> memory;

    public Path (int id, PointInterface start, PointInterface end) {
        this.id = id;
        this.pathFromStart = new LinkedList<>();
        this.pathFromEnd = new LinkedList<>();
        this.memory = new LinkedList<>();
        this.pathFromStart.add(start);
        this.pathFromEnd.add(end);
    }

    @Override
    public int getId () {
        return id;
    }

    @Override
    public PointInterface getLastPointFromStart () {
        return this.pathFromStart.get(this.pathFromStart.size() - 1);
    }

    @Override
    public PointInterface getLastPointFromEnd () {
        return this.pathFromEnd.get(this.pathFromEnd.size() - 1);
    }

    @Override
    public PointInterface getStart () {
        return this.pathFromStart.get(0);
    }

    @Override
    public PointInterface getEnd () {
        return this.pathFromEnd.get(0);
    }

    @Override
    public boolean isComplete () {
        return this.pathFromEnd.get(this.pathFromEnd.size() - 1).isNeighbour(this.pathFromStart.get(this.pathFromStart.size() - 1)) &&
                isPartComplete(this.pathFromStart) &&
                isPartComplete(this.pathFromEnd);
    }

    @Override
    public boolean addWaypoint (PointInterface point) {
        if (point.isNeighbour(this.getLastPointFromStart())) {
            this.pathFromStart.add(point);
            this.memory.add(1);
            return true;
        } else if (point.isNeighbour(this.getLastPointFromEnd())) {
            this.pathFromEnd.add(point);
            this.memory.add(-1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PointInterface getLastSetWaypoint () {
        int flag = getLastWaypointFlag();
        if (flag == 0) {
            return getStart(); // list is empty
        } else {
            if (flag == 1) {
                return getLastPointFromStart();
            } else {
                return getLastPointFromEnd();
            }
        }
    }

    @Override
    public PointInterface removeLastSetWaypoint () {
        PointInterface point = getLastSetWaypoint();
        if (!(point.equals(getStart()) || point.equals(getEnd()))) {
            this.memory.remove(memory.size() - 1);
            return removeWaypoint(point);
        } else {
            return null;
        }
    }

    @Override
    public List<PointInterface> getAllPoints () {
        List<PointInterface> list = new ArrayList<>();
        list.addAll(this.pathFromStart);
        list.addAll(this.pathFromEnd);
        return list;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();

        for (PointInterface point : this.pathFromStart) {
            result.append(point).append(", ");
        }
        if (!isComplete()) {
            result.append("... ");
        }
        for (int i = this.pathFromEnd.size() - 1; i >= 0; i--) {
            result.append(this.pathFromEnd.get(i)).append(", ");
        }
        return result.toString();
    }

    private PointInterface removeWaypoint (PointInterface point) {
        if (this.pathFromStart.get(this.pathFromStart.size() - 1).equals(point)) {
            this.pathFromStart.remove(point);
            return point;
        } else if (this.pathFromEnd.get(this.pathFromEnd.size() - 1).equals(point)) {
            this.pathFromEnd.remove(point);
            return point;
        } else {
            return null;
        }
    }

    private boolean isPartComplete (List<PointInterface> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            if (!points.get(i).isNeighbour(points.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private int getLastWaypointFlag () {
        if (this.memory.isEmpty()) {
            return 0;
        } else {
            return this.memory.get(this.memory.size() - 1);
        }
    }
}
