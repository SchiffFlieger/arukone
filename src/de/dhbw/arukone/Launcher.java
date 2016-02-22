package de.dhbw.arukone;

import de.dhbw.arukone.reader.BoardReader;

import java.io.IOException;
import java.util.Map;

public class Launcher {
    public static int size = 5;

    public static void main(String... args) throws IOException {
        BoardReader reader = new BoardReader();
        Map<String, ArukoneBoard> map = reader.readAllBoardsInDirectory("res/boards");
        for (String key :
                map.keySet()) {
            System.out.println(map.get(key));
            System.out.println("-----------------");
        }
        System.out.printf("size: %d", map.size());
    }

    private static void solve(int k) {
        ArukoneSolver solver = new ArukoneSolver();
        ArukoneBoard board = null;
        long start, end;

        System.out.printf("%dx%d -- init\n", k, k);
        System.out.println(board);
        start = System.nanoTime();
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("%dx%d -- solution -- time: %.6fs -- iterations: %d\n", k, k, (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();
    }
}



