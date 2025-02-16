package com.swiggy.gameoflife;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        location.makeHabitable();

        assertTrue(location.isOccupied());
    }

    @Test
    public void testIsOccupied_ReturnsFalse_WhenCellIsNotPresent() {
        Location location = new Location(0, 0, false);

        assertFalse(location.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsNotHabitable_WhenOccupiedAndHasZeroNeighbors() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        new Location(1, 1, false);

        location00.updateState(occupiedLocations, 3, 3);

        assertFalse(location00.isHabitable());
        assertFalse(location00.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsNotHabitable_WhenOccupiedAndHasOneNeighbor() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        new Location(1, 0, false);
        new Location(1, 1, false);
        new Location(2, 0, false);
        new Location(2, 1, false);

        location00.updateState(occupiedLocations, 3, 2);

        assertFalse(location00.isHabitable());
        assertFalse(location00.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsNotHabitable_WhenOccupiedAndHasFourNeighbors() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        Location location10 = new Location(1, 0, true);
        location10.makeHabitable();
        occupiedLocations.add(location10);

        Location location11 = new Location(1, 1, true);
        location11.makeHabitable();
        occupiedLocations.add(location11);

        Location location20 = new Location(2, 0, true);
        location20.makeHabitable();
        occupiedLocations.add(location20);

        new Location(2, 1, false);

        location11.updateState(occupiedLocations, 3, 2);

        assertFalse(location11.isHabitable());
        assertFalse(location11.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsNotHabitable_WhenOccupiedAndHasFiveNeighbors() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        Location location10 = new Location(1, 0, true);
        location10.makeHabitable();
        occupiedLocations.add(location10);

        Location location11 = new Location(1, 1, true);
        location11.makeHabitable();
        occupiedLocations.add(location11);

        Location location20 = new Location(2, 0, true);
        location20.makeHabitable();
        occupiedLocations.add(location20);

        Location location21 = new Location(2, 1, true);
        location21.makeHabitable();
        occupiedLocations.add(location21);

        location11.updateState(occupiedLocations, 3, 2);

        assertFalse(location11.isHabitable());
        assertFalse(location11.isOccupied());
    }

    @Test
    public void testUpdateState_SetsLocationAsHabitable_WhenNotOccupiedAndHasThreeNeighbors() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        Location location10 = new Location(1, 0, true);
        location10.makeHabitable();
        occupiedLocations.add(location10);

        Location location11 = new Location(1, 1, false);

        new Location(2, 0, false);
        new Location(2, 1, false);


        location11.updateState(occupiedLocations, 3, 2);

        assertTrue(location11.isHabitable());
        assertTrue(location11.isOccupied());
    }

    @Test
    public void testUpdateState_CountsOccupiedNeighborsCorrectly_WhenNeighborsAreOccupied() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        Location location10 = new Location(1, 0, true);
        location10.makeHabitable();
        occupiedLocations.add(location10);

        Location location11 = new Location(1, 1, false);

        location11.updateState(occupiedLocations, 3, 3);

        assertTrue(location11.isHabitable());
        assertTrue(location11.isOccupied());
    }

    @Test
    public void testUpdateState_DoesNotCountOutOfBoundsNeighbors_WhenLocationIsAtEdge() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        Location location10 = new Location(1, 0, true);
        location10.makeHabitable();
        occupiedLocations.add(location10);

        Location location11 = new Location(1, 1, false);

        location11.updateState(occupiedLocations, 2, 2);

        assertTrue(location11.isHabitable());
        assertTrue(location11.isOccupied());
    }

    @Test
    public void testUpdateState_DoesNotCountOutOfBoundsNeighbors_WhenLocationIsOutOfBounds() {
        Set<Location> occupiedLocations = new HashSet<>();

        Location location00 = new Location(0, 0, true);
        location00.makeHabitable();
        occupiedLocations.add(location00);

        Location location01 = new Location(0, 1, true);
        location01.makeHabitable();
        occupiedLocations.add(location01);

        Location location10 = new Location(1, 0, true);
        location10.makeHabitable();
        occupiedLocations.add(location10);

        Location location11 = new Location(1, 1, false);

        location11.updateState(occupiedLocations, 1, 1);

        assertFalse(location11.isHabitable());
        assertFalse(location11.isOccupied());
    }
}
