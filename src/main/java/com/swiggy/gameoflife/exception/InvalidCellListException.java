package com.swiggy.gameoflife.exception;

public class InvalidCellListException extends IllegalArgumentException {
    public InvalidCellListException() {
        super(ExceptionMessages.INVALID_CELL_LIST);
    }
}