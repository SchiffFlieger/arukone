package de.dhbw.arukone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karsten on 19.01.16.
 */
public class Launcher {
    public static void main(String... args) {
        ArukoneBoard board = initEasyField();
//        ArukoneBoard board = initDifficultField();
        GeneticAlgorithmArukone algorithm = new GeneticAlgorithmArukone(board);
        System.out.println(board);
        algorithm.presolve();
        System.out.println("--------------------------");
        System.out.println(board);
    }

    private static ArukoneBoard initEasyField() {
        ArukoneBoard board = new ArukoneBoard(Configuration.instance.size);
        board.addPath(new Path(new Point(0, 0), new Point(4, 1)));
        board.addPath(new Path(new Point(0, 2), new Point(3, 1)));
        board.addPath(new Path(new Point(1, 2), new Point(4, 2)));
        board.addPath(new Path(new Point(0, 4), new Point(3, 3)));
        board.addPath(new Path(new Point(1, 4), new Point(4, 3)));
        return board;
    }

    private static ArukoneBoard initDifficultField() {
        ArukoneBoard board = new ArukoneBoard(Configuration.instance.size);
        board.addPath(new Path(new Point(1,1), new Point(4,4)));
        board.addPath(new Path(new Point(1,2), new Point(2,3)));
        board.addPath(new Path(new Point(1,3), new Point(2,7)));
        board.addPath(new Path(new Point(2,4), new Point(2,6)));
        board.addPath(new Path(new Point(3,7), new Point(5,1)));
        board.addPath(new Path(new Point(4,1), new Point(4,3)));
        board.addPath(new Path(new Point(5,0), new Point(6,2)));
        board.addPath(new Path(new Point(6,1), new Point(5,8)));
        board.addPath(new Path(new Point(7,1), new Point(5,7)));
        return board;
    }
}