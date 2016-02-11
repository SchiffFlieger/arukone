package de.dhbw.arukone;

public enum Configuration {
    instance;

    boolean isDifficult = true;
    int size = (isDifficult ? 9 : 5);
}
