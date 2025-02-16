package com.swiggy.gameoflife;

import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        Scanner scanner = new Scanner(System.in);
        GameRunner gameRunner = new GameRunner(grid, scanner);
        gameRunner.startGame(10, 10, 30);
    }
}
