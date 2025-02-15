package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Grid {

    //    private final int rows;
//    private final int cols;
//    List<List<Cell>> cells;
//    private final Random random = new Random();
//
//    public Grid(int rows, int cols) {
//        checkValidGridDimensions(rows, cols);
//        this.rows = rows;
//        this.cols = cols;
//        this.cells = new ArrayList<>();
//        initializeGrid();
//    }
//
//    private void initializeGrid() {
//        for (int i = 0; i < rows; i++) {
//            List<Cell> row = new ArrayList<>();
//            for (int j = 0; j < cols; j++) {
//                row.add(new Cell(CellState.DEAD));
//            }
//            cells.add(row);
//        }
//    }
//
//    public void display() {
//        for (List<Cell> row : cells) {
//            for (Cell cell : row) {
//                cell.printCell();
//            }
//        }
//    }
//
//    public boolean areAllCellsDead() {
//        for (List<Cell> row : cells) {
//            for (Cell cell : row) {
//                if (cell.isAlive()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public void update() {
//        List<List<Cell>> nextGeneration = new ArrayList<>();
//        for (int i = 0; i < rows; i++) {
//            List<Cell> row = new ArrayList<>();
//            for (int j = 0; j < cols; j++) {
//                row.add(cells.get(i).get(j).nextState(countAliveNeighbors(i, j)));
//            }
//            nextGeneration.add(row);
//        }
//        this.cells = nextGeneration;
//    }
//
//    private int countAliveNeighbors(int row, int col) {
//        int count = 0;
//        int[] directions = {-1, 0, 1};
//        for (int dr : directions) {
//            for (int dc : directions) {
//                if (dr == 0 && dc == 0) continue;
//                int newRow = row + dr;
//                int newCol = col + dc;
//                if (isWithinBounds(newRow, newCol) && cells.get(newRow).get(newCol).isAlive()) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//
//    private boolean isWithinBounds(int row, int col) {
//        return row >= 0 && row < rows && col >= 0 && col < cols;
//    }
//
//    public void seedRandomCells(int rows, int cols, int seedPercentage) {
//        checkValidGridDimensions(rows, cols);
//        checkValidSeedPercentage(seedPercentage);
//        int totalCells = rows * cols;
//        int aliveCells = totalCells * seedPercentage / 100;
//        Set<String> processedCells = new HashSet<>();
//
//        int i = 0;
//        while (i < aliveCells) {
//            int row = random.nextInt(rows);
//            int col = random.nextInt(cols);
//            String cellKey = row + "," + col;
//
//            if (!processedCells.contains(cellKey) && !cells.get(row).get(col).isAlive()) {
//                cells.get(row).get(col).revive();
//                processedCells.add(cellKey);
//                i++;
//            }
//        }
//    }
//
//    private static void checkValidGridDimensions(int rows, int cols) {
//        if (rows <= 0 || cols <= 0) {
//            throw new InvalidGridDimensionsException();
//        }
//    }
//
//    private static void checkValidSeedPercentage(int seedPercentage) {
//        if (seedPercentage < 0 || seedPercentage > 100) {
//            throw new InvalidSeedPercentageException();
//        }
//    }
    private final int rows;
    private final int cols;
    private final List<List<Location>> dimensions;

    public Grid(int rows, int cols, List<List<Location>> dimensions) {
        checkValidGridDimensions(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.dimensions = dimensions;
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            List<Location> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(new Location(i, j));
            }
            dimensions.add(row);
        }
    }

    public void seedRandomCells(int rows, int cols, int seedPercentage) {
    }

    private static void checkValidGridDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new InvalidGridDimensionsException();
        }
    }

    public void display() {
        for (List<Location> locationList : dimensions) {
            for (Location location : locationList) {
                System.out.print(location);
            }
            System.out.println();
        }
    }

    public boolean areAllCellsDead() {
        return true;
    }

    public void update() {
    }
}