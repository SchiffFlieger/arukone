package de.dhbw.arukone;


import de.dhbw.arukone.faster.FastArukoneBoard;
import de.dhbw.arukone.faster.FastArukoneSolver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static int size;

    public static void main (String... args) throws IOException {
        FastArukoneBoard board = new FastArukoneBoard("test", 5);
        board.addFixedPath(1, 0, 0, 4, 1);
        board.addFixedPath(2, 0, 2, 3, 1);
        board.addFixedPath(3, 1, 2, 4, 2);
        board.addFixedPath(4, 0, 4, 3, 3);
        board.addFixedPath(5, 1, 4, 4, 3);

        FastArukoneSolver solver = new FastArukoneSolver();
        solver.solve(board, 1);
    }

    private static String createElapsedTimeString (long start, long end) {
        long elapsedTime = end - start;

        return String.format("%02dh %02dm %02ds %04dms",
                TimeUnit.MILLISECONDS.toHours(elapsedTime),
                TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % TimeUnit.MINUTES.toSeconds(1),
                TimeUnit.MILLISECONDS.toMillis(elapsedTime) % TimeUnit.SECONDS.toMillis(1));
    }
}



