package com.swiggy.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testIsHabitable_ReturnTrue_WhenIsHabitableIsTrue() {
        Location location = new Location(1, 2, true);
        assertTrue(location.isHabitable());
    }

    @Test
    public void testMakeHabitable_SetsLocationAsHabitable_WhenCalled() {
        Location location = new Location(0, 0, false);
        location.makeHabitable();
        assertTrue(location.isHabitable());
    }

    @Test
    public void testIsOccupied_ReturnsTrue_WhenCellIsPresent() {
        Location location = new Location(0, 0, false);
        Cell cell = new Cell(location);
        location = new Location(0, 0, false, cell);
        assertTrue(location.isOccupied());
    }

    @Test
    public void testIsOccupied_ReturnsFalse_WhenCellIsNotPresent() {
        Location location = new Location(0, 0, false);
        assertFalse(location.isOccupied());
    }

}
