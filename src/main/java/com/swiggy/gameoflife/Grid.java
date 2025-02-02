package com.swiggy.gameoflife;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {
    private final int m;
    private final int n;
    private final Cell[][] cells;
    private final Random random = new Random();

    public Grid(int m, int n, Cell[][] cells) {
        this.m = m;
        this.n = n;
        this.cells = cells;
    }

    public void display() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(cells[i][j].isAlive() ? CellState.ALIVE : CellState.DEAD);
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
