package de.dhbw.arukone;


import de.dhbw.arukone.observer.CellChangedListener;
import de.dhbw.arukone.reader.BoardReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static int size;

    public static void main (String... args) throws IOException {
        FastArukoneBoard board = new BoardReader().readBoard("res/boards/5x5_1.xml");
        board.addListener(new CellChangedListener());
        FastArukoneSolver solver = new FastArukoneSolver();

        System.out.println("-- arukone solver");
        System.out.println("initial board:");
        System.out.println(board);
        System.out.println();

        System.out.println("-- start solving");
        long start = System.currentTimeMillis();
        solver.solve(board, 1);
        long end = System.currentTimeMillis();

        System.out.printf("-- board solved -- time: %s -- iterations: %d\n", createElapsedTimeString(start, end), FastArukoneSolver.iterations);
        System.out.println("solved board:");
        System.out.println(board);
    }

    private static String createElapsedTimeString (long start, long end) {
        long elapsedTime = end - start;

        return String.format("%02dh %02dm %02ds %03dms",
                TimeUnit.MILLISECONDS.toHours(elapsedTime),
                TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % TimeUnit.MINUTES.toSeconds(1),
                TimeUnit.MILLISECONDS.toMillis(elapsedTime) % TimeUnit.SECONDS.toMillis(1));
    }
}



