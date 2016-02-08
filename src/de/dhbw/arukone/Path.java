package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karsten on 06.02.16.
 */
public class Path {
    private static int id_counter = 1;
    private final int id;
    private List<Point> pathFromStart;
    private List<Point> pathFromEnd;

    public Path(Point start, Point end) {
        this.id = id_counter;
        id_counter++;
        this.pathFromStart = new ArrayList<>();
        this.pathFromEnd = new ArrayList<>();
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
            return true;
        } else if (point.isReachable(this.getLastPointFromEnd())) {
            this.pathFromEnd.add(point);
            System.out.printf("add point %s to end of path %d\n", point, id);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeWaypoint(Point point) {
        if (this.pathFromStart.get(this.pathFromStart.size()-1).equals(point)) {
            this.pathFromStart.remove(point);
            return true;
        } else if (this.pathFromEnd.get(this.pathFromEnd.size()-1).equals(point)) {
            this.pathFromEnd.remove(point);
            return true;
        } else {
            return false;
        }
    }

    public List<Point> getAllPoints() {
        List<Point> list = new ArrayList<>();
        list.addAll(this.pathFromStart);
        list.addAll(this.pathFromEnd);
        return list;
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
