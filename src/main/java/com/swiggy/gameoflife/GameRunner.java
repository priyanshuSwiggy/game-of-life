package com.swiggy.gameoflife;

import java.util.Scanner;

public class GameRunner {
    private final Grid grid;
    private final Scanner scanner;

    public GameRunner(Grid grid, Scanner scanner) {
        this.grid = grid;
        this.scanner = scanner;
    }

    public void startGame(int rows, int cols, int seedPercentage) {
        try {
            grid.seedRandomCells(rows, cols, seedPercentage);
            while (true) {
                grid.display();
                if (grid.areAllCellsDead() || "q".equalsIgnoreCase(scanner.nextLine())) {
                    break;
                }
                grid.update();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
