package com.swiggy.gameoflife;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        GameRunner gameRunner = new GameRunner(grid, new java.util.Scanner(System.in));
        gameRunner.startGame(10, 10, 15);
    }
}
