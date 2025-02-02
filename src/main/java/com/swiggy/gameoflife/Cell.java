package com.swiggy.gameoflife;

public class Cell {
    private CellState state;
    public boolean isAlive() {
        return state.equals(CellState.ALIVE);
    }
    public void makeAlive() {
        this.state = CellState.ALIVE;
    }
}
