package de.dhbw.sudoku;

public class Application {
    public static void main(String... args) {
        long runtimeInSeconds;

        GeneticAlgorithmSudoku geneticAlgorithmSudoku = new GeneticAlgorithmSudoku(10);

        runtimeInSeconds = System.currentTimeMillis();

        System.out.println("globalBestPossibleFitness : " + Configuration.instance.globalBestPossibleFitness);
        System.out.println();

        System.out.println("--- first generation - result");
        System.out.println(geneticAlgorithmSudoku.getFirst());
        System.out.println("fitness : " + geneticAlgorithmSudoku.getFirstScore());
        System.out.println();

        System.out.println("solving in progress...");

        geneticAlgorithmSudoku.generateDefaultPopulation(10);
        geneticAlgorithmSudoku.run(Configuration.instance.maximumNumberOfGenerations);

        System.out.println("--- global best result");
        System.out.println(geneticAlgorithmSudoku.getGlobalBestSudokuBoard());
        System.out.println("fitness : " + geneticAlgorithmSudoku.getGlobalBestFitness());
        System.out.println();

        System.out.println("runtimeInSeconds : " + (System.currentTimeMillis() - runtimeInSeconds) / 1000);
    }
}