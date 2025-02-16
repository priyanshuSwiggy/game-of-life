package com.swiggy.gameoflife;

import java.util.Map;

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

    public void updateState(Map<String, Boolean> occupiedLocations, int rows, int cols) {
        int count = countOccupiedNeighbors(occupiedLocations, rows, cols);
        if (isOccupied() && (count <= 1 || count >= 4)) {
            this.isHabitable = false;
            occupiedLocations.put(rows+","+cols, false);
            this.cell = null;
        } else if (!isOccupied() && count == 3) {
            this.isHabitable = true;
            occupiedLocations.put(row+","+col, true);
            this.cell = new Cell(this);
        }
    }

    private int countOccupiedNeighbors(Map<String, Boolean> occupiedLocations, int rows, int cols) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                int newRow = this.row + dr;
                int newCol = this.col + dc;
                if (isWithinBounds(newRow, newCol, rows, cols) && occupiedLocations.getOrDefault(newRow+","+newCol, false)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isWithinBounds(int newRow, int newCol, int rows, int cols) {
        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;
    }
}
