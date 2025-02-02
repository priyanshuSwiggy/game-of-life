package com.swiggy.gameoflife.exception;

public class InvalidGridDimensionsException extends IllegalArgumentException {
    public InvalidGridDimensionsException() {
        super(ExceptionMessages.INVALID_GRID_DIMENSIONS);
    }
}