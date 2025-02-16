package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;

import java.util.*;

public class Grid {
    private final int rows;
    private final int cols;
    private final List<List<Location>> dimensions;
    private final Map<String, Boolean> occupiedLocations;
    private final Random random = new Random();

    public Grid(int rows, int cols) {
        checkValidGridDimensions(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.dimensions = new ArrayList<>();
        this.occupiedLocations = new HashMap<>();
        initializeGrid();
    }

    public Grid(int rows, int cols, List<List<Location>> dimensions, Map<String, Boolean> occupiedLocations) {
        checkValidGridDimensions(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.dimensions = dimensions;
        this.occupiedLocations = occupiedLocations;
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            List<Location> locations = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                locations.add(new Location(i, j, false));
            }
            dimensions.add(locations);
        }
    }

    public void seedRandomCells(int rows, int cols, int seedPercentage) {
        checkValidSeedPercentage(seedPercentage);
        int dimension = rows * cols;
        int habitableLocation = dimension * seedPercentage / 100;
        Set<String> processedLocation = new HashSet<>();

        int i =0;
        while(i<habitableLocation){
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            String locationKey = row + "," + col;

            Location location = dimensions.get(row).get(col);
            if(!processedLocation.contains(locationKey) && !location.isOccupied()){
                location.makeHabitable();
                occupiedLocations.put(locationKey, true);
                new Cell(location);
                processedLocation.add(locationKey);
                i++;
            }
        }
    }

    private void checkValidGridDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new InvalidGridDimensionsException();
        }
    }

    private void checkValidSeedPercentage(int seedPercentage) {
        if (seedPercentage <= 0 || seedPercentage >= 100) {
            throw new InvalidSeedPercentageException();
        }
    }

    public void display() {
        for (List<Location> locations : dimensions) {
            for (Location location : locations) {
                if(location.isOccupied())
                    System.out.print("*");
                else
                    System.out.print("_");
            }
            System.out.println();
        }
    }

    public boolean areAllCellsDead() {
        for (List<Location> locations : dimensions) {
            for (Location location : locations) {
                if (location.isHabitable()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void update(int rows, int cols) {
        for (List<Location> locations : dimensions) {
            for (Location location : locations) {
                location.updateState(occupiedLocations, rows, cols);
            }
        }
    }
}