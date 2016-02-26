package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {
    private final int id;
    private List<Point> pathFromStart;
    private List<Point> pathFromEnd;
    private List<Integer> memory;

    public Path (int id, Point start, Point end) {
        this.id = id;
        this.pathFromStart = new LinkedList<>();
        this.pathFromEnd = new LinkedList<>();
        this.memory = new LinkedList<>();
        this.pathFromStart.add(start);
        this.pathFromEnd.add(end);
    }

    public Path (Path path) {
        this.id = path.getId();
        this.pathFromStart = new LinkedList<>();
        this.pathFromEnd = new LinkedList<>();
        this.memory = new ArrayList<>();
        this.pathFromStart.addAll(path.pathFromStart.stream().map(Point::new).collect(Collectors.toList()));
        this.pathFromEnd.addAll(path.pathFromEnd.stream().map(Point::new).collect(Collectors.toList()));
        this.memory.addAll(path.memory.stream().collect(Collectors.toList()));
    }

    public int getId () {
        return id;
    }

    public Point getLastPointFromStart () {
        return this.pathFromStart.get(this.pathFromStart.size() - 1);
    }

    public Point getLastPointFromEnd () {
        return this.pathFromEnd.get(this.pathFromEnd.size() - 1);
    }

    public Point getStart () {
        return this.pathFromStart.get(0);
    }

    public Point getEnd () {
        return this.pathFromEnd.get(0);
    }

    public boolean isComplete () {
        return this.pathFromEnd.get(this.pathFromEnd.size() - 1).isReachable(this.pathFromStart.get(this.pathFromStart.size() - 1)) &&
                isPartComplete(this.pathFromStart) &&
                isPartComplete(this.pathFromEnd);
    }

    private boolean isPartComplete (List<Point> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            if (!points.get(i).isReachable(points.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public boolean addWaypoint (Point point) {
        if (point.isReachable(this.getLastPointFromStart())) {
            this.pathFromStart.add(point);
            this.memory.add(1);
            return true;
        } else if (point.isReachable(this.getLastPointFromEnd())) {
            this.pathFromEnd.add(point);
            this.memory.add(-1);
            return true;
        } else {
            return false;
        }
    }

    private int getLastWaypointFlag () {
        if (this.memory.isEmpty()) {
            return 0;
        } else {
            return this.memory.get(this.memory.size() - 1);
        }
    }

    public Point getLastSetWaypoint () {
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

    public Point removeWaypoint (Point point) {
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

    public Point removeLastSetWaypoint () {
        Point point = getLastSetWaypoint();
        if (!(point.equals(getStart()) || point.equals(getEnd()))) {
            this.memory.remove(memory.size() - 1);
            return removeWaypoint(point);
        } else {
            return null;
        }
    }

    public List<Point> getAllPoints () {
        List<Point> list = new ArrayList<>();
        list.addAll(this.pathFromStart);
        list.addAll(this.pathFromEnd);
        return list;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();

        for (Point point : this.pathFromStart) {
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
}
