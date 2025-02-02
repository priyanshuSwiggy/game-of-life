package com.swiggy.gameoflife;

public class Cell {
    private CellState state;
    public boolean isAlive() {
        return this.state == CellState.ALIVE;
    }
    public void makeAlive() {
        this.state = CellState.ALIVE;
    }
    public void kill() {
        this.state = CellState.DEAD;
    }
}
