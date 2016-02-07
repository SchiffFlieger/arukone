package de.dhbw.arukone;

import java.util.ArrayList;

/**
 * Created by karsten on 06.02.16.
 */
public class Path {
    private static int id_counter = 1;
    private final int id;
    private Point start, end;
    private ArrayList<Point> path;

    public Path() {
        this.id = id_counter;
        id_counter++;
        this.path = new ArrayList<>();
    }

    public Path(Point start, Point end) {
        this();
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public Point getLastWaypoint() {
        return this.path.get(this.path.size()-1);
    }

    public boolean isComplete() {
        if (this.path.isEmpty()) {
            if (this.start.isReachable(this.end)) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < this.path.size()-1; i++) {
            if (!this.path.get(i).isReachable(this.path.get(i+1))) {
                return false;
            }
        }

        if (!this.start.isReachable(this.path.get(0))) {
            return false;
        }

        if (!this.getLastWaypoint().isReachable(this.end)) {
            return false;
        }

        return true;
    }

    public boolean addWaypoint(Point point) {
        if (this.path.isEmpty()) {
            if (this.start.isReachable(point)) {
                this.path.add(point);
                return true;
            }
        } else {
            if (this.getLastWaypoint().isReachable(point)) {
                this.path.add(point);
                return true;
            }
        }
        return false;
    }

    public Point getEnd() {
        return end;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public Point getStart() {
        return start;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(start + ", ");
        for (Point point : this.path) {
            result.append(point + ", ");
        }
        if (!isComplete()) {
            result.append("... ");
        }
        result.append(end + "\n");

        return result.toString();
    }
}
