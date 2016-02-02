package de.dhbw.sudoku;

import java.util.ArrayList;

public class GeneticAlgorithmSudoku {
    private int currentGeneration;
    private ArrayList<SudokuBoard> population;
    private ArrayList<Integer> populationScore;
    private ArrayList<SudokuBoard> matingSelections;
    private SudokuBoard initSudokuBoard;
    private SudokuBoard firstRunSudokuBoard;
    private SudokuBoard globalBestSudokuBoard;
    private int globalBestFitness;

    public GeneticAlgorithmSudoku(int initPopulationSize) {
        population = new ArrayList<>(initPopulationSize);
        populationScore = new ArrayList<>(initPopulationSize);
        matingSelections = new ArrayList<>(initPopulationSize);
        generateDefaultPopulation(initPopulationSize);
        globalBestFitness = 0;
    }

    public GeneticAlgorithmSudoku(SudokuBoard sudokuBoard,int initPopulationSize) {
        initSudokuBoard = sudokuBoard;
        population = new ArrayList<>(initPopulationSize + 1);
        populationScore = new ArrayList<>(initPopulationSize + 1);
        matingSelections = new ArrayList<>(initPopulationSize + 1);
        generatePopulation(sudokuBoard, initPopulationSize);
    }

    public void run(int maximumNumberOfGenerations) {
        calculateFitnessOfFirstGeneration();

        while (currentGeneration < maximumNumberOfGenerations && globalBestFitness < Configuration.instance.globalBestPossibleFitness) {
            selection();
            for (int i = 0;i < matingSelections.size();i+=2)
                doCrossover(matingSelections.get(i),matingSelections.get(i+1));
            ++currentGeneration;

            if (Configuration.instance.isDebug) {
                if (currentGeneration % 1000 == 0)
                    System.out.println("currentGeneration : " + currentGeneration);
            }
        }
    }

    public void generatePopulation(SudokuBoard sudokuBoard,int basePopulation) {
        SudokuBoard tempSudokuBoard;
        population.add(sudokuBoard);

        for (int i = 0; i < basePopulation; i++) {
            tempSudokuBoard = new SudokuBoard(sudokuBoard);
            tempSudokuBoard.fillEmptyFields();
            firstRunSudokuBoard = tempSudokuBoard;
            population.add(tempSudokuBoard);
        }
    }

    public void generateDefaultPopulation(int basePopulation) {
        SudokuBoard tempSudokuBoard;

        for (int i = 0;i < basePopulation;i++) {
            tempSudokuBoard = new SudokuBoard(true);
            tempSudokuBoard.fillEmptyFields();
            firstRunSudokuBoard = tempSudokuBoard;
            population.add(tempSudokuBoard);
        }
    }

    public int calculateFitness(SudokuBoard sudokuBoard) {
        return calculateFitnessOfRows(sudokuBoard.getBoard()) + calculateFitnessOfColumns(sudokuBoard.getBoard()) + scoreSquares(sudokuBoard.getBoard());
    }

    private void sortPopulation() {
        int populationSize = population.size();
        SudokuBoard tempSudokuBoard;
        int tempFitness;

        for (int i = 0;i < populationSize;i++) {
            for (int j = i+1;j < populationSize;j++) {
                if (populationScore.get(j) > populationScore.get(i)) {
                    tempSudokuBoard = population.get(i);
                    tempFitness = populationScore.get(i);

                    population.set(i,population.get(j));
                    populationScore.set(i,populationScore.get(j));

                    population.set(j,tempSudokuBoard);
                    populationScore.set(j,tempFitness);
                }
            }
        }
    }

    private void selection() {
        matingSelections.clear();
        sortPopulation();

        int maximumIndex = (population.size() > 100) ? 100 : population.size();

        for (int i = 0;i < maximumIndex;i++)
            matingSelections.add(population.get(i));

        if (matingSelections.size() % 2 != 0)
            matingSelections.add(matingSelections.get(Configuration.instance.randomGenerator.nextInt(matingSelections.size()-1)));

        while (population.size() > 100) {
            population.remove(population.size()-1);
            populationScore.remove(population.size()-1);
        }
    }

    private void doCrossover(SudokuBoard parent1,SudokuBoard parent2) {
        int fitnessChild1, fitnessChild2;
        SudokuBoard child1 = new SudokuBoard(parent1);
        SudokuBoard child2 = new SudokuBoard(parent2);

        for (int xPoint = Configuration.instance.randomGenerator.nextInt(9);xPoint < 9;xPoint++)
            for (int yPoint = Configuration.instance.randomGenerator.nextInt(9);yPoint < 9;yPoint++) {
                child1.setValueAt(xPoint,yPoint,parent2.getValueAt(xPoint,yPoint));
                child2.setValueAt(xPoint,yPoint,parent1.getValueAt(xPoint,yPoint));
            }

        double randomNumber = Configuration.instance.randomGenerator.nextDouble(0,1);
        if (randomNumber < Configuration.instance.mutationProbability) {
            doMutation(child1);
            doMutation(child2);
        }

        fitnessChild1 = calculateFitness(child1);
        fitnessChild2 = calculateFitness(child2);

        if (fitnessChild1 > globalBestFitness) {
            if (Configuration.instance.isDebug)
                System.out.println("fitness improvement " + currentGeneration + " - " + globalBestFitness);
            globalBestFitness = fitnessChild1;
            globalBestSudokuBoard = child1;
        }

        if (fitnessChild2 > globalBestFitness) {
            if (Configuration.instance.isDebug)
                System.out.println("fitness improvement " + currentGeneration + " - " + globalBestFitness);
            globalBestFitness = fitnessChild2;
            globalBestSudokuBoard = child2;
        }

        population.add(child1);
        populationScore.add(fitnessChild1);

        population.add(child2);
        populationScore.add(fitnessChild2);
    }

    private void doMutation(SudokuBoard sudokuBoard) {
        int column = Configuration.instance.randomGenerator.nextInt(9);
        int row1 = Configuration.instance.randomGenerator.nextInt(9);
        int row2 = Configuration.instance.randomGenerator.nextInt(9);

        while (!sudokuBoard.swap(column,row1,row2)) {
            row1 = Configuration.instance.randomGenerator.nextInt(9);
            row2 = Configuration.instance.randomGenerator.nextInt(9);
        }
    }

    private void calculateFitnessOfFirstGeneration() {
        int currentBestFitness;

        for (int i = 0;i < population.size();i++) {
            currentBestFitness = calculateFitness(population.get(i));

            if (currentBestFitness > globalBestFitness) {
                globalBestFitness = currentBestFitness;
                globalBestSudokuBoard = population.get(i);
            }

            populationScore.add(currentBestFitness);
        }
    }

    public SudokuBoard getInitSudokuBoard() {
        return initSudokuBoard;
    }

    public SudokuBoard getFirst() {
        return firstRunSudokuBoard;
    }

    public int getFirstScore() {
        return calculateFitnessOfRows(firstRunSudokuBoard.getBoard()) + calculateFitnessOfColumns(firstRunSudokuBoard.getBoard()) + scoreSquares(firstRunSudokuBoard.getBoard());
    }

    public SudokuBoard getGlobalBestSudokuBoard() {
        return globalBestSudokuBoard;
    }

    public int getGlobalBestFitness() {
        return globalBestFitness;
    }

    private int calculateFitnessOfRows(int[][] sudokuBoard) {
        int[] numberOfDuplicates;
        int fitness = 0;

        for (int row = 0;row < sudokuBoard.length;row++) {
            numberOfDuplicates = new int[10];

            for (int column = 0;column < sudokuBoard[row].length;column++)
                numberOfDuplicates[sudokuBoard[row][column]]++;

            for (int x = 1;x < numberOfDuplicates.length;x++)
                if (numberOfDuplicates[x] == 1)
                    ++fitness;
        }

        return fitness;
    }

    private int calculateFitnessOfColumns(int[][] sudokuBoard) {
        int[] numberOfDuplicates;
        int fitness = 0;

        for (int column = 0;column < sudokuBoard.length;column++) {
            numberOfDuplicates = new int[10];

            for (int row = 0;row < sudokuBoard[column].length;row++)
                numberOfDuplicates[sudokuBoard[row][column]]++;

            for (int x = 1;x < numberOfDuplicates.length;x++)
                if (numberOfDuplicates[x] == 1)
                    ++fitness;
        }

        return fitness;
    }

    private int scoreSquares(int[][] sudokuBoard) {
        int[] numberOfDuplicates;
        int fitness = 0;
        int row = 0;
        int column = 0;
        int maximumIndex1 = row + 3;
        int maximumIndex2 = column + 3;

        while (row < sudokuBoard.length) {
            numberOfDuplicates = new int[10];

            while (row < maximumIndex1) {
                column = maximumIndex2 - 3;
                while (column < maximumIndex2) {
                    numberOfDuplicates[sudokuBoard[row][column]]++;
                    ++column;
                }
                ++row;
            }

            for (int x = 1; x < numberOfDuplicates.length;x++)
                if (numberOfDuplicates[x] == 1)
                    ++fitness;

            if (maximumIndex2 < sudokuBoard.length){
                row = maximumIndex1 - 3;
                column = maximumIndex2;
                maximumIndex2+= 3;
            } else {
                column = 0;
                maximumIndex2 = 3;
                row = maximumIndex1;
                maximumIndex1 = row + 3;
            }
        }

        return fitness;
    }
}