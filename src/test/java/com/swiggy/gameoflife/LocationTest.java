package com.swiggy.gameoflife;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testUpdateState_SetsLocationAsNotHabitable_WhenOccupiedAndHasOneNeighbor() {
        Map<String, Boolean> occupiedLocations = new HashMap<>();
        Location location00 = new Location(0, 0, true);
        Cell cell00 = new Cell(location00);
        location00 = new Location(0, 0, true, cell00);
        occupiedLocations.put("0,0", true);

        Location location01 = new Location(0, 1, false);

        Location location10 = new Location(1, 0, false);

        Location location11 = new Location(1, 1, false);

        Location location20 = new Location(2, 0, false);

        Location location21 = new Location(2, 1, false);

        location11.updateState(occupiedLocations, 3, 2);

        assertFalse(location11.isHabitable());
        assertFalse(location11.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsNotHabitable_WhenOccupiedAndHasFourNeighbors() {
        Map<String, Boolean> occupiedLocations = new HashMap<>();
        Location location00 = new Location(0, 0, true);
        Cell cell00 = new Cell(location00);
        location00 = new Location(0, 0, true, cell00);
        occupiedLocations.put("0,0", true);

        Location location01 = new Location(0, 1, true);
        Cell cell01 = new Cell(location01);
        location01 = new Location(0, 1, true, cell01);
        occupiedLocations.put("0,1", true);

        Location location10 = new Location(1, 0, true);
        Cell cell10 = new Cell(location10);
        location10 = new Location(1, 0, true, cell10);
        occupiedLocations.put("1,0", true);

        Location location11 = new Location(1, 1, false);

        Location location20 = new Location(2, 0, true);
        Cell cell20 = new Cell(location20);
        location20 = new Location(2, 0, true, cell20);
        occupiedLocations.put("2,0", true);

        Location location21 = new Location(2, 1, true);
        Cell cell21 = new Cell(location21);
        location21 = new Location(2, 1, true, cell21);
        occupiedLocations.put("2,1", true);

        location11.updateState(occupiedLocations, 3, 2);

        assertFalse(location11.isHabitable());
        assertFalse(location11.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsHabitable_WhenNotOccupiedAndHasThreeNeighbors() {
        Map<String, Boolean> occupiedLocations = new HashMap<>();
        Location location00 = new Location(0, 0, true);
        Cell cell00 = new Cell(location00);
        location00 = new Location(0, 0, true, cell00);
        occupiedLocations.put("0,0", true);

        Location location01 = new Location(0, 1, true);
        Cell cell01 = new Cell(location01);
        location01 = new Location(0, 1, true, cell01);
        occupiedLocations.put("0,1", true);

        Location location10 = new Location(1, 0, true);
        Cell cell10 = new Cell(location10);
        location10 = new Location(1, 0, true, cell10);
        occupiedLocations.put("1,0", true);

        Location location11 = new Location(1, 1, false);
        Location location20 = new Location(2, 0, false);
        Location location21 = new Location(2, 1, false);


        location11.updateState(occupiedLocations, 3, 2);
        assertTrue(location11.isHabitable());
        assertTrue(location11.isOccupied());
    }

    @Test
    public void testUpdateState_CountsOccupiedNeighborsCorrectly_WhenNeighborsAreOccupied() {
        Map<String, Boolean> occupiedLocations = new HashMap<>();
        Location location00 = new Location(0, 0, true);
        Cell cell00 = new Cell(location00);
        location00 = new Location(0, 0, true, cell00);
        occupiedLocations.put("0,0", true);

        Location location01 = new Location(0, 1, true);
        Cell cell01 = new Cell(location01);
        location01 = new Location(0, 1, true, cell01);
        occupiedLocations.put("0,1", true);

        Location location10 = new Location(1, 0, true);
        Cell cell10 = new Cell(location10);
        location10 = new Location(1, 0, true, cell10);
        occupiedLocations.put("1,0", true);

        Location location11 = new Location(1, 1, false);
        location11.updateState(occupiedLocations, 3, 3);

        assertTrue(location11.isHabitable());
        assertTrue(location11.isOccupied());
    }

    @Test
    public void testUpdateState_DoesNotCountOutOfBoundsNeighbors_WhenLocationIsAtEdge() {
        Map<String, Boolean> occupiedLocations = new HashMap<>();
        Location location00 = new Location(0, 0, true);
        Cell cell00 = new Cell(location00);
        location00 = new Location(0, 0, true, cell00);
        occupiedLocations.put("0,0", true);

        Location location01 = new Location(0, 1, true);
        Cell cell01 = new Cell(location01);
        location01 = new Location(0, 1, true, cell01);
        occupiedLocations.put("0,1", true);

        Location location10 = new Location(1, 0, true);
        Cell cell10 = new Cell(location10);
        location10 = new Location(1, 0, true, cell10);
        occupiedLocations.put("1,0", true);

        Location location11 = new Location(1, 1, false);
        location11.updateState(occupiedLocations, 2, 2);

        assertTrue(location11.isHabitable());
        assertTrue(location11.isOccupied());
    }

    @Test
    public void testUpdateState_DoesNotCountOutOfBoundsNeighbors_WhenLocationIsOutOfBounds() {
        Map<String, Boolean> occupiedLocations = new HashMap<>();
        Location location00 = new Location(0, 0, true);
        Cell cell00 = new Cell(location00);
        location00 = new Location(0, 0, true, cell00);
        occupiedLocations.put("0,0", true);

        Location location01 = new Location(0, 1, true);
        Cell cell01 = new Cell(location01);
        location01 = new Location(0, 1, true, cell01);
        occupiedLocations.put("0,1", true);

        Location location10 = new Location(1, 0, true);
        Cell cell10 = new Cell(location10);
        location10 = new Location(1, 0, true, cell10);
        occupiedLocations.put("1,0", true);

        Location location11 = new Location(1, 1, false);
        location11.updateState(occupiedLocations, 1, 1);

        assertFalse(location11.isHabitable());
        assertFalse(location11.isOccupied());
    }
}
