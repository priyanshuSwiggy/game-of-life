package com.swiggy.gameoflife;

public enum CellState {
    ALIVE('*'), DEAD('_');

    private final char symbol;
    CellState(char symbol){
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}
