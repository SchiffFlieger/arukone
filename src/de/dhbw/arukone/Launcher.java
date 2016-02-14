package de.dhbw.arukone;

import java.io.IOException;

public class Launcher {
    public static int size = 5;
    private static final boolean doPresolve = true;

    public static void main(String... args) throws IOException {
        ArukoneSolver solver = new ArukoneSolver();
        ArukoneBoard board;
        long start, end;

        board = init5x5();
        System.out.println("5x5 -- init");
        System.out.println(board);
        start = System.nanoTime();
        if (doPresolve) {
            solver.presolve(board);
            System.out.println("5x5 -- after presolve");
            System.out.println(board);
        }
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("5x5 -- solution -- time: %.6fs -- iterations: %d\n", (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();

        board = init6x6();
        System.out.println("6x6 -- init");
        System.out.println(board);
        start = System.nanoTime();
        if (doPresolve) {
            solver.presolve(board);
            System.out.println("6x6 -- after presolve");
            System.out.println(board);
        }
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("6x6 -- solution -- time: %.6fs -- iterations: %d\n", (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();

        board = init7x7();
        System.out.println("7x7 -- init");
        System.out.println(board);
        start = System.nanoTime();
        if (doPresolve) {
            solver.presolve(board);
            System.out.println("7x7 -- after presolve");
            System.out.println(board);
        }
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("7x7 -- solution -- time: %.6fs -- iterations: %d\n", (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();

        board = init8x8();
        System.out.println("8x8 -- init");
        System.out.println(board);
        start = System.nanoTime();
        if (doPresolve) {
            solver.presolve(board);
            System.out.println("8x8 -- after presolve");
            System.out.println(board);
        }
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("8x8 -- solution -- time: %.6fs -- iterations: %d\n", (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();

        board = init9x9();
        System.out.println("9x9 -- init");
        System.out.println(board);
        start = System.nanoTime();
        if (doPresolve) {
            solver.presolve(board);
            System.out.println("9x9 -- after presolve");
            System.out.println(board);
        }
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("9x9 -- solution -- time: %.6fs -- iterations: %d\n", (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();

        board = init10x10();
        System.out.println("10x10 -- init");
        System.out.println(board);
        start = System.nanoTime();
        if (doPresolve) {
            solver.presolve(board);
            System.out.println("10x10 -- after presolve");
            System.out.println(board);
        }
        board = solver.solve(board, 1);
        end = System.nanoTime();
        System.out.printf("10x10 -- solution -- time: %.6fs -- iterations: %d\n", (end - start) / 1000000000.0, ArukoneSolver.getIterations());
        System.out.println(board);
        System.out.println();
        ArukoneSolver.reset();
    }

    private static ArukoneBoard init5x5() {
        Launcher.size = 5;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(0, 0), new Point(4, 1)));
        board.addPath(new Path(new Point(0, 2), new Point(3, 1)));
        board.addPath(new Path(new Point(1, 2), new Point(4, 2)));
        board.addPath(new Path(new Point(0, 4), new Point(3, 3)));
        board.addPath(new Path(new Point(1, 4), new Point(4, 3)));
        return board;
    }

    private static ArukoneBoard init6x6() {
        Launcher.size = 6;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(0, 0), new Point(4, 0)));
        board.addPath(new Path(new Point(0, 1), new Point(5, 0)));
        board.addPath(new Path(new Point(0, 2), new Point(2, 2)));
        board.addPath(new Path(new Point(0, 4), new Point(3, 2)));
        board.addPath(new Path(new Point(0, 5), new Point(5, 2)));
        board.addPath(new Path(new Point(1, 4), new Point(4, 2)));
        return board;
    }

    private static ArukoneBoard init7x7() {
        Launcher.size = 7;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(0, 6), new Point(6, 5)));
        board.addPath(new Path(new Point(1, 5), new Point(2, 1)));
        board.addPath(new Path(new Point(1, 6), new Point(5, 4)));
        board.addPath(new Path(new Point(3, 3), new Point(4, 2)));
        board.addPath(new Path(new Point(3, 4), new Point(6, 6)));
        board.addPath(new Path(new Point(4, 4), new Point(5, 5)));
        return board;
    }

    private static ArukoneBoard init8x8() {
        Launcher.size = 8;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(0, 4), new Point(7, 1)));
        board.addPath(new Path(new Point(1, 4), new Point(2, 7)));
        board.addPath(new Path(new Point(1, 6), new Point(2, 4)));
        board.addPath(new Path(new Point(1, 7), new Point(2, 5)));
        board.addPath(new Path(new Point(3, 3), new Point(4, 4)));
        board.addPath(new Path(new Point(3, 4), new Point(5, 4)));
        return board;
    }

    private static ArukoneBoard init9x9() {
        Launcher.size = 9;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(1, 1), new Point(4, 4)));
        board.addPath(new Path(new Point(1, 2), new Point(2, 3)));
        board.addPath(new Path(new Point(1, 3), new Point(2, 7)));
        board.addPath(new Path(new Point(2, 4), new Point(2, 6)));
        board.addPath(new Path(new Point(3, 7), new Point(5, 1)));
        board.addPath(new Path(new Point(4, 1), new Point(4, 3)));
        board.addPath(new Path(new Point(5, 0), new Point(6, 2)));
        board.addPath(new Path(new Point(6, 1), new Point(5, 8)));
        board.addPath(new Path(new Point(7, 1), new Point(5, 7)));
        return board;
    }

    private static ArukoneBoard init10x10() {
        Launcher.size = 10;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(1,1), new Point(4,4)));
        board.addPath(new Path(new Point(2,2), new Point(4,7)));
        board.addPath(new Path(new Point(2,4), new Point(5,6)));
        board.addPath(new Path(new Point(1,4), new Point(5,8)));
        board.addPath(new Path(new Point(2,1), new Point(2,7)));
        board.addPath(new Path(new Point(1,6), new Point(1,8)));
        board.addPath(new Path(new Point(6,3), new Point(3,9)));
        board.addPath(new Path(new Point(3,1), new Point(7,9)));
        board.addPath(new Path(new Point(4,1), new Point(8,2)));
        board.addPath(new Path(new Point(6,2), new Point(8,8)));
        return board;
    }
}



