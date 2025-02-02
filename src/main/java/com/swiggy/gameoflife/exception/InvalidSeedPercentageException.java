package com.swiggy.gameoflife.exception;

public class InvalidSeedPercentageException extends IllegalArgumentException {
    public InvalidSeedPercentageException() {
        super(ExceptionMessages.INVALID_SEED_PERCENTAGE);
    }
}