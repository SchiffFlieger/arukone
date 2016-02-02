package de.dhbw.sudoku;

public enum Configuration {
    instance;

    boolean isSudokuDifficult = true;
    MersenneTwister randomGenerator = new MersenneTwister(System.currentTimeMillis());
    int globalBestPossibleFitness = 9 * 9 * 3;
    int maximumNumberOfGenerations = 100000;
    double mutationProbability = 0.05;
    boolean isDebug = false;
}