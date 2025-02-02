package com.swiggy.gameoflife;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {
    private final int m;
    private final int n;
    private Cell[][] cells;
    private final Random random = new Random();

    public Grid(int m, int n) {
        this.m = m;
        this.n = n;
        this.cells = new Cell[m][n];
        initializeCells();
    }

    public Grid(int m, int n, Cell[][] cells) {
        this.m = m;
        this.n = n;
        this.cells = cells != null ? cells : new Cell[m][n];
        if (cells == null) {
            initializeCells();
        }
    }

    private void initializeCells() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void display() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(cells[i][j].printCell());
            }
            System.out.println();
        }
    }

    public boolean isAllDead() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void update() {
        Cell[][] nextGeneration = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nextGeneration[i][j] = cells[i][j].nextState(countAliveNeighbors(i, j));
            }
        }
        this.cells = nextGeneration;
    }

    private int countAliveNeighbors(int m, int n) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                int row = m + dr;
                int col = n + dc;
                if (isWithinBoundary(row, col) && cells[row][col].isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isWithinBoundary(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }


    public void randomSeeding(int m, int n, int seedPercentage) {
        int totalCells = m * n;
        int aliveCells = totalCells * seedPercentage / 100;
        Set<String> processedCells = new HashSet<>();

        int i=0;
        while (i < aliveCells) {
            int row = random.nextInt(m);
            int col = random.nextInt(n);
            String cellKey = row + "," + col;

            if (!processedCells.contains(cellKey) && !cells[row][col].isAlive()) {
                cells[row][col].makeAlive();
                processedCells.add(cellKey);
                i++;
            }
        }
    }
}
