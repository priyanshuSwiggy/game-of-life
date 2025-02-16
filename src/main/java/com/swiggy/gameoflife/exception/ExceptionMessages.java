package com.swiggy.gameoflife.exception;

public class ExceptionMessages {
    private ExceptionMessages() {
    }
    public static final String INVALID_GRID_DIMENSIONS = "Grid dimensions must be positive integers";
    public static final String INVALID_SEED_PERCENTAGE = "Seed percentage must be between 0 and 100";
    public static final String NEVER_ENDING_CYCLE= "Never ending cycle detected";
}