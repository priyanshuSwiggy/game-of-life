package com.swiggy.gameoflife;

public class Grid {
    private final int m;
    private final int n;
    private final Cell[][] cells;

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

    public void seed(int m, int n, int seedPercentage) {
    }
}
