package de.dhbw.arukone;


import de.dhbw.arukone.faster.FastArukoneBoard;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static int size;

    public static void main (String... args) throws IOException {
        FastArukoneBoard board = new FastArukoneBoard("test", 5);
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



