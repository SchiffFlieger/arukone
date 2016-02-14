package de.dhbw.arukone.test;

import de.dhbw.arukone.Point;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testGetX() throws Exception {
        Point point = new Point(0, 1);
        int x = 0;
        assertEquals(x, point.getX());
    }

    @Test
    public void testGetY() throws Exception {
        Point point = new Point(0, 1);
        int y = 1;
        assertEquals(y, point.getY());
    }

    @Test
    public void testEquals() throws Exception {
        Point one = new Point(1, 2);
        Point two = new Point(1, 2);

        assertEquals(one, one);
        assertEquals(two, two);
        assertEquals(one, two);
    }

    @Test
    public void testIsReachable() throws Exception {
        Point one = new Point(0, 0);
        Point two = new Point(0, 1);
        Point three = new Point(1, 1);

        assertEquals(true, one.isReachable(two));
        assertEquals(true, two.isReachable(three));
        assertEquals(false, one.isReachable(three));
    }

    @Test
    public void testLeft() throws Exception {
        Point one = new Point(1, 1);
        Point two = new Point(0, 0);

        assertEquals(new Point(1, 0), one.left());
        assertNull(two.left());
    }

    @Test
    public void testRight() throws Exception {
        Point one = new Point(1, 0);
        Point two = new Point(4, 4);

        assertEquals(new Point(1, 1), one.right());
        assertNull(two.right());
    }

    @Test
    public void testUp() throws Exception {
        Point one = new Point(1, 1);
        Point two = new Point(0, 0);

        assertEquals(new Point(0, 1), one.up());
        assertNull(two.up());
    }

    @Test
    public void testDown() throws Exception {
        Point one = new Point(1, 0);
        Point two = new Point(4, 0);

        assertEquals(new Point(2, 0), one.down());
        assertNull(two.down());
    }
}