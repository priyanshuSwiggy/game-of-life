package com.swiggy.gameoflife;

import java.util.Scanner;

public class GameRunner {
    private final Grid grid;
    private Scanner scanner;
    public GameRunner(Grid grid, Scanner scanner) {
        this.grid = grid;
        this.scanner = scanner;
    }

    public void startGame(int m, int n, int seedPercentage) {
        while(true) {
            grid.seed(m, n, seedPercentage);
            grid.display();
            if (grid.isAllDead() || "q".equals(scanner.nextLine())) {
                break;
            }
            grid.update();
        }
    }
}
