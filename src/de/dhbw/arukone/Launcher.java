package de.dhbw.arukone;


import de.dhbw.arukone.reader.BoardReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Launcher {

    private static final String BOARD_5 = "res/boards/5x5.xml";
    private static final String BOARD_6 = "res/boards/6x6.xml";
    private static final String BOARD_7 = "res/boards/7x7.xml";
    private static final String BOARD_8 = "res/boards/8x8.xml";
    private static final String BOARD_9 = "res/boards/9x9.xml";
    private static final String BOARD_10 = "res/boards/10x10.xml";
    private static final String BOARD_11 = "res/boards/11x11.xml";
    private static final String BOARD_12 = "res/boards/12x12.xml";

    public static void main (String... args) throws IOException {
        FastArukoneBoard board = new BoardReader().readBoard(BOARD_9);
        FastArukoneSolver solver = new FastArukoneSolver();

        System.out.println("-- arukone solver");
        System.out.println("initial board: " + board.getIdentifier());
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



