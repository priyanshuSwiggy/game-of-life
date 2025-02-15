package com.swiggy.gameoflife;

public class Location {
    private final int row;
    private final int col;
    private boolean isHabitable;
    private Cell cell;

    public Location(int row, int col, boolean isHabitable) {
        this.row = row;
        this.col = col;
        this.isHabitable = isHabitable;
    }

    public Location(int row, int col, boolean isHabitable, Cell cell) {
        this.row = row;
        this.col = col;
        this.isHabitable = isHabitable;
        this.cell = cell;
    }

    public boolean isHabitable() {
        return this.isHabitable;
    }

    public void makeHabitable() {
        this.isHabitable = true;
    }

    public boolean isOccupied() {
        return this.cell != null;
    }

    public void updateState() {

    }
}
