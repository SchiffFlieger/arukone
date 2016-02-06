package de.dhbw.arukone;

/**
 * Created by karsten on 19.01.16.
 */
public class Launcher {
    public static void main(String... args) {
        ArukoneBoard board = new ArukoneBoard();
        GeneticAlgorithmArukone algorithm = new GeneticAlgorithmArukone(board);

        System.out.println(board.advancedToString());
        System.out.println();
        System.out.println();

        algorithm.solve();
        System.out.println(board.advancedToString());

    }
}
