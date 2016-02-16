package de.dhbw.arukone;

import java.io.IOException;

public class Launcher {
    public static int size = 5;
    private static final boolean doPresolve = true;

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

        switch (k) {
            case 5:
                board = init5x5();
                break;
            case 6:
                board = init6x6();
                break;
            case 7:
                board = init7x7();
                break;
            case 8:
                board = init8x8();
                break;
            case 9:
                board = init9x9();
                break;
            case 10:
                board = init10x10();
                break;
            case 11:
                board = init11x11();
                break;
            case 12:
                board = init12x12();
                break;
        }

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

    private static ArukoneBoard init11x11() {
        Launcher.size = 11;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(2,8), new Point(9,7)));
        board.addPath(new Path(new Point(6,3), new Point(10,5)));
        board.addPath(new Path(new Point(3,0), new Point(8,5)));
        board.addPath(new Path(new Point(1,1), new Point(1,5)));
        board.addPath(new Path(new Point(3,3), new Point(9,9)));
        board.addPath(new Path(new Point(6,1), new Point(7,10)));
        board.addPath(new Path(new Point(9,1), new Point(6,8)));
        board.addPath(new Path(new Point(0,7), new Point(4,8)));
        return board;
    }

    private static ArukoneBoard init12x12() {
        Launcher.size = 12;
        Path.reset();
        ArukoneBoard board = new ArukoneBoard(Launcher.size);
        board.addPath(new Path(new Point(6,2), new Point(10,7)));
        board.addPath(new Path(new Point(9,3), new Point(11,5)));
        board.addPath(new Path(new Point(10,1), new Point(6,5)));
        board.addPath(new Path(new Point(1,1), new Point(8,5)));
        board.addPath(new Path(new Point(2,3), new Point(8,1)));
        board.addPath(new Path(new Point(8,0), new Point(3,5)));
        board.addPath(new Path(new Point(0,6), new Point(4,3)));
        board.addPath(new Path(new Point(4,7), new Point(10,10)));
        board.addPath(new Path(new Point(4,8), new Point(6,8)));
        board.addPath(new Path(new Point(2,8), new Point(6,4)));
        board.addPath(new Path(new Point(6,7), new Point(8,11)));
        return board;
    }
}



