package com.swiggy.gameoflife.exception;

public class NeverEndingCycleException extends IllegalArgumentException {
    public NeverEndingCycleException() {
        super(ExceptionMessages.NEVER_ENDING_CYCLE);
    }
}