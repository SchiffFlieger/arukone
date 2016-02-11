package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by karsten on 06.02.16.
 */
public class Path {
    private static int id_counter = 1;
    private final int id;
    private List<Point> pathFromStart;
    private List<Point> pathFromEnd;
    private List<Point> blockedPoints;
    private List<Integer> memory;

    public Path(Point start, Point end) {
        this.id = id_counter;
        id_counter++;
        this.pathFromStart = new ArrayList<>();
        this.pathFromEnd = new ArrayList<>();
        this.memory = new LinkedList<>();
        this.blockedPoints = new LinkedList<>();
        this.pathFromStart.add(start);
        this.pathFromEnd.add(end);
    }

    public int getId() {
        return id;
    }

    public Point getLastPointFromStart() {
        return this.pathFromStart.get(this.pathFromStart.size()-1);
    }

    public Point getLastPointFromEnd() {
        return this.pathFromEnd.get(this.pathFromEnd.size()-1);
    }

    public Point getStart() {
        return this.pathFromStart.get(0);
    }

    public Point getEnd() {
        return this.pathFromEnd.get(0);
    }

    public boolean isComplete() {
        if (this.pathFromEnd.get(this.pathFromEnd.size() - 1).isReachable(this.pathFromStart.get(this.pathFromStart.size() - 1)) &&
                isPartComplete(this.pathFromStart) &&
                isPartComplete(this.pathFromEnd)) {
            return true;
        }
        return false;
    }

    private boolean isPartComplete(List<Point> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            if (!points.get(i).isReachable(points.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public boolean addWaypoint(Point point) {
        if (point.isReachable(this.getLastPointFromStart())) {
            System.out.printf("add point %s to start of path %d\n", point, id);
            this.pathFromStart.add(point);
            this.memory.add(1);
            return true;
        } else if (point.isReachable(this.getLastPointFromEnd())) {
            System.out.printf("add point %s to end of path %d\n", point, id);
            this.pathFromEnd.add(point);
            this.memory.add(-1);
            return true;
        } else {
            return false;
        }
    }

    public Point removeWaypoint(Point point) {
        if (this.pathFromStart.get(this.pathFromStart.size()-1).equals(point)) {
            this.pathFromStart.remove(point);
            System.out.printf("remove point %s from start of path %d\n", point, id);
            return point;
        } else if (this.pathFromEnd.get(this.pathFromEnd.size()-1).equals(point)) {
            this.pathFromEnd.remove(point);
            System.out.printf("remove point %s from end of path %d\n", point, id);
            return point;
        } else {
            return null;
        }
    }

    public Point removeLastSetWaypoint() {
        int flag = getLastWaypointFlag();
        if (flag == 0) {
            return null; // list is empty
        } else {
            if (flag == 1) {
                this.memory.remove(memory.size()-1);
                return removeWaypoint(getLastPointFromStart());
            } else {
                this.memory.remove(memory.size()-1);
                return removeWaypoint(getLastPointFromEnd());
            }
        }
    }

    private int getLastWaypointFlag() {
        if (this.memory.isEmpty()) {
            return 0;
        } else {
            return this.memory.get(this.memory.size()-1);
        }
    }

    public List<Point> getAllPoints() {
        List<Point> list = new ArrayList<>();
        list.addAll(this.pathFromStart);
        list.addAll(this.pathFromEnd);
        return list;
    }

    public void addBlockedPoint(Point point) {
        this.blockedPoints.add(point);
    }

    public boolean isBlocked(Point point) {
        return this.blockedPoints.contains(point);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Point point : this.pathFromStart) {
            result.append(point + ", ");
        }
        if (!isComplete()) {
            result.append("... ");
        }
        for (int i = this.pathFromEnd.size()-1; i >= 0; i--) {
            result.append(this.pathFromEnd.get(i) + ", ");
        }

        return result.toString();
    }
}
