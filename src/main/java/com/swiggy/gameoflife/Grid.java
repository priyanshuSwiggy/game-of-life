package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;
import com.swiggy.gameoflife.exception.NeverEndingCycleException;

import java.util.*;

public class Grid {
    private final int rows;
    private final int cols;
    private final List<List<Location>> dimensions;
    private final Set<Location> occupiedLocations;
    private final Random random = new Random();
    private final Map<String, Integer> stateHistory = new HashMap<>();
    private static final int MAX_CYCLE_COUNT = 3;

    public Grid(int rows, int cols) {
        validateGridDimensions(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.dimensions = new ArrayList<>();
        this.occupiedLocations = new HashSet<>();
        initializeGrid();
    }

    public Grid(int rows, int cols, List<List<Location>> dimensions, Set<Location> occupiedLocations) {
        validateGridDimensions(rows, cols);
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

    public void seedRandomCells(int rows, int cols, double seedPercentage) {
        validateSeedPercentage(seedPercentage);
        int totalLocations = rows * cols;
        int habitableLocations = totalLocations * (int) seedPercentage / 100;
        Set<Location> processedLocations = new HashSet<>();

        while(processedLocations.size() < habitableLocations){
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            Location location = dimensions.get(row).get(col);
            if (!processedLocations.contains(location) && !location.isOccupied()) {
                location.makeHabitable();
                occupiedLocations.add(location);
                processedLocations.add(location);
            }
        }
    }

    private void validateGridDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new InvalidGridDimensionsException();
        }
    }

    private void validateSeedPercentage(double seedPercentage) {
        if (seedPercentage <= 0 || seedPercentage >= 100) {
            throw new InvalidSeedPercentageException();
        }
    }

    public void display() {
        for (List<Location> locations : dimensions) {
            for (Location location : locations) {
                System.out.print(location.isOccupied() ? CellState.ALIVE : CellState.DEAD);
            }
            System.out.println();
        }
    }

    public boolean areAllCellsDead() {
        return occupiedLocations.isEmpty();
    }

    public void update(int rows, int cols) {
        String currentState = getCurrentState();
        stateHistory.put(currentState, stateHistory.getOrDefault(currentState, 0) + 1);

        if (stateHistory.get(currentState) >= MAX_CYCLE_COUNT) {
            throw new NeverEndingCycleException();
        }
        for (List<Location> locations : dimensions) {
            for (Location location : locations) {
                location.updateState(occupiedLocations, rows, cols);
            }
        }
    }

    public String getCurrentState() {
        StringBuilder stateBuilder = new StringBuilder();
        for (List<Location> locations : dimensions) {
            for (Location location : locations) {
                stateBuilder.append(location.isOccupied() ? CellState.ALIVE : CellState.DEAD);
            }
            stateBuilder.append("\n");
        }
        return stateBuilder.toString();
    }
}