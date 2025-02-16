package com.swiggy.gameoflife;

import java.util.Objects;
import java.util.Set;

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

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isHabitable() {
        return this.isHabitable;
    }

    public void makeHabitable() {
        this.isHabitable = true;
        this.cell = new Cell(this);
    }

    public boolean isOccupied() {
        return this.cell != null;
    }

    public void updateState(Set<Location> occupiedLocations, int rows, int cols) {
        int count = countOccupiedNeighbors(occupiedLocations, rows, cols);
        if (isOccupied() && (count <= 1 || count >= 4)) {
            this.isHabitable = false;
            occupiedLocations.remove(this);
            this.cell = null;
        }
        if (!isOccupied() && count == 3) {
            this.isHabitable = true;
            occupiedLocations.add(this);
            this.cell = new Cell(this);
        }
    }

    private int countOccupiedNeighbors(Set<Location> occupiedLocations, int rows, int cols) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                int newRow = this.row + dr;
                int newCol = this.col + dc;
                Location neighbouringLocation = new Location(newRow, newCol);
                if (isWithinBounds(newRow, newCol, rows, cols) && occupiedLocations.contains(neighbouringLocation)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isWithinBounds(int newRow, int newCol, int rows, int cols) {
        return newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return row == location.row && col == location.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
