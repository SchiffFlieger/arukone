package de.dhbw.arukone;

import de.dhbw.arukone.reader.BoardReader;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Launcher {
    public static int size = 5;

    public static void main(String... args) throws IOException {
        BoardReader reader = new BoardReader();
        ObservableList<ArukoneBoard> list = reader.readAllBoardsInDirectory("res/boards");
        ArukoneSolver solver = new ArukoneSolver();
        ArukoneBoard board = list.get(9);
        System.out.println(board);
        System.out.println("--------------------");
        System.out.println(board.deepToString());
        System.out.println("--------------------");
        System.out.println(solver.solve(board, 1));
        System.out.println(ArukoneSolver.iterations);
        System.out.println(board.deepToString());
    }

    private static void solve(int k) {
        ArukoneSolver solver = new ArukoneSolver();
        ArukoneBoard board = null;
        long start, end;

        System.out.printf("%dx%d -- init\n", k, k);
        System.out.println(board);
        start = System.nanoTime();
        solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("%dx%d -- solution -- time: %.6fs -- iterations: %d\n", k, k, (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();
    }
}



