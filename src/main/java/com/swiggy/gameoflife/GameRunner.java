package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;
import com.swiggy.gameoflife.exception.NeverEndingCycleException;

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
        } catch (InvalidGridDimensionsException | InvalidSeedPercentageException | NeverEndingCycleException e) {
            System.err.println(e.getMessage());
        }
    }
}
