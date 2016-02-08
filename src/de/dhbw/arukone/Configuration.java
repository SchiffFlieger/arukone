package de.dhbw.arukone;

/**
 * created by Karsten Köhler on 02.02.2016
 */
public enum Configuration {
    instance;

    MersenneTwisterFast twister = new MersenneTwisterFast(System.nanoTime());
    int maximumNumberOfGenerations = 100 * 100;
    double mutationPropability = 0.05;
    boolean isDifficult = false;
    int size = 5;
}
