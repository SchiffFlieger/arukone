package de.dhbw.arukone.test;

import de.dhbw.arukone.Path;
import de.dhbw.arukone.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathTest {

    @Before
    public void reset() {
        Path.reset();
    }

    @Test
    public void testGetId() throws Exception {
        Path one = new Path(new Point(0,0), new Point(1,1));
        Path two = new Path(new Point(2,0), new Point(3,1));
        assertEquals(1, one.getId());
        assertEquals(2, two.getId());
    }

    @Test
    public void testGetLastPointFromStart() throws Exception {
        Path one = new Path(new Point(0,0), new Point(3,1));

        assertEquals(new Point(0,0), one.getLastPointFromStart());

        one.addWaypoint(new Point(1,0));
        assertEquals(new Point(1,0), one.getLastPointFromStart());
    }

    @Test
    public void testGetLastPointFromEnd() throws Exception {
        Path one = new Path(new Point(0,0), new Point(3,1));

        assertEquals(new Point(3,1), one.getLastPointFromEnd());

        one.addWaypoint(new Point(3,0));
        assertEquals(new Point(3,0), one.getLastPointFromEnd());
    }

    @Test
    public void testGetStart() throws Exception {
        Path one = new Path(new Point(0,0), new Point(3,1));
        one.addWaypoint(new Point(1,0));

        assertEquals(new Point(0,0), one.getStart());
    }

    @Test
    public void testGetEnd() throws Exception {
        Path one = new Path(new Point(0,0), new Point(3,1));
        one.addWaypoint(new Point(3,0));

        assertEquals(new Point(3,1), one.getEnd());
    }

    @Test
    public void testIsComplete() throws Exception {
        Path one = new Path(new Point(0,0), new Point(3,1));
        assertEquals(false, one.isComplete());

        one.addWaypoint(new Point(1,0));
        assertEquals(false, one.isComplete());

        one.addWaypoint(new Point(3,0));
        assertEquals(false, one.isComplete());

        one.addWaypoint(new Point(2,0));
        assertEquals(true, one.isComplete());
    }

    @Test
    public void testGetLastSetWaypoint() throws Exception {
        Path one = new Path(new Point(0,0), new Point(3,1));
        one.addWaypoint(new Point(1,0));
        one.addWaypoint(new Point(2,0));
        one.addWaypoint(new Point(3,0));
        assertEquals(new Point(3,0), one.getLastSetWaypoint());
    }
}