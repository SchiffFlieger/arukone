package de.dhbw.arukone;

import de.dhbw.arukone.reader.BoardReader;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Launcher {
    public static int size = 5;

    public static void main(String... args) throws IOException {
        BoardReader reader = new BoardReader();
        ObservableList<ArukoneBoard> list = reader.readAllBoardsInDirectory("res/boards");
        ArukoneBoard board = list.get(10);
        ArukoneSolver solver = new ArukoneSolver(board, 500);
//        board.addListener(new BoardChangeListener(board));
        System.out.println(board);
        System.out.println("--------------------");
        System.out.println(board.deepToString());
        System.out.println(solver.solve(1));
        System.out.println(ArukoneSolver.iterations);
    }
}



