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

    public void addWaypoint(Point point) {
        if (this.path.isEmpty()) {

        }
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
}
