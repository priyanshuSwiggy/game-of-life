package com.swiggy.gameoflife;

public class Cell {
//    private CellState state;
//
//    public Cell(CellState state) {
//        this.state = state;
//    }
//
//    public boolean isAlive() {
//        return this.state == CellState.ALIVE;
//    }
//
//    public void revive() {
//        this.state = CellState.ALIVE;
//    }
//
//    public void kill() {
//        this.state = CellState.DEAD;
//    }
//
//    public Cell nextState(int aliveNeighbors) {
//        if (shouldKill(aliveNeighbors)) {
//            return new Cell(CellState.DEAD);
//        }
//        if (shouldRevive(aliveNeighbors)) {
//            return new Cell(CellState.ALIVE);
//        }
//        return this;
//    }
//
//    private boolean shouldKill(int aliveNeighbors) {
//        return isAlive() && (aliveNeighbors <= 1 || aliveNeighbors >= 4);
//    }
//
//    private boolean shouldRevive(int aliveNeighbors) {
//        return !isAlive() && aliveNeighbors == 3;
//    }
//
//    public char printCell() {
//        return state.getSymbol();
//    }
    private Location location;

    public Cell(Location location) {
        this.location = location;
    }
}
