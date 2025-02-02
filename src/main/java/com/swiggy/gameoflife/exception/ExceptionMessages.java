package com.swiggy.gameoflife.exception;

public class ExceptionMessages {
    public static final String INVALID_GRID_DIMENSIONS = "Grid dimensions must be positive integers";
    public static final String INVALID_CELL_LIST = "Cells list cannot be null or empty";
    public static final String INVALID_SEED_PERCENTAGE = "Seed percentage must be between 0 and 100";
    public static final String NEVER_ENDING_CYCLE = "The grid is stuck in a never-ending cycle";
}