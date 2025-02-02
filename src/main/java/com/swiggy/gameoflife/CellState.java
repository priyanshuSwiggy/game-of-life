package com.swiggy.gameoflife;

public enum CellState {
    DEAD('_'), ALIVE('*');

    private final char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }
}
