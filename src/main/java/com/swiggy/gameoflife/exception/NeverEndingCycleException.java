package com.swiggy.gameoflife.exception;

public class NeverEndingCycleException extends RuntimeException {
    public NeverEndingCycleException() {
        super(ExceptionMessages.NEVER_ENDING_CYCLE);
    }
}