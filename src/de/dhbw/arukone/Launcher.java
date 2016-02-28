package de.dhbw.arukone;

import de.dhbw.arukone.reader.BoardReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static int size;

    public static void main(String... args) throws IOException {
        ArukoneBoard board = new BoardReader().readBoard("res/boards/8x8_2.xml");
        size = board.getSize();

        ArukoneSolver solver = new ArukoneSolver();

        System.out.println("-- arukone solver");
        System.out.println("initial board:");
        System.out.println(board.deepToString());
        System.out.println();

        System.out.println("-- start solving");
        long start = System.currentTimeMillis();
        solver.solve(board, 1);
        long end = System.currentTimeMillis();

        System.out.printf("-- board solved -- time: %s -- iterations: %d\n", createElapsedTimeString(start, end), ArukoneSolver.iterations);
        System.out.println("solved board:");
        System.out.println(board.deepToString());
    }

    private static String createElapsedTimeString(long start, long end) {
        long elapsedTime = end - start;

        return String.format("%02dh %02dm %02ds %04dms",
                TimeUnit.MILLISECONDS.toHours(elapsedTime),
                TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % TimeUnit.MINUTES.toSeconds(1),
                TimeUnit.MILLISECONDS.toMillis(elapsedTime) % TimeUnit.SECONDS.toMillis(1));
    }
}



