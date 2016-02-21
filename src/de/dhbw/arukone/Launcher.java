package de.dhbw.arukone;

import java.io.IOException;

public class Launcher {
    public static int size = 5;

    public static void main(String... args) throws IOException {
        solve(5);
        solve(6);
        solve(7);
        solve(8);
        solve(9);
        solve(10);
        solve(11);
        solve(12);
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



