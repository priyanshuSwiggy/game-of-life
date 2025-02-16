package com.swiggy.gameoflife;

import java.util.Scanner;

public class GameRunner {
    private final Grid grid;
    private final Scanner scanner;

    public GameRunner(Grid grid, Scanner scanner) {
        this.grid = grid;
        this.scanner = scanner;
    }

    public void startGame(int m, int n, int seedPercentage) {
        try {
            grid.seedRandomCells(m, n, seedPercentage);
            while (true) {
                grid.display();
                if (grid.areAllCellsDead() || "q".equalsIgnoreCase(scanner.nextLine())) {
                    break;
                }
                grid.update(m, n);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
