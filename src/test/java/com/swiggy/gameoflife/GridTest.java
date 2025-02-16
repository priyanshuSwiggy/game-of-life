package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;
import com.swiggy.gameoflife.exception.NeverEndingCycleException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GridTest {

    private Grid grid;

    @Mock
    private Location location00;

    @Mock
    private Location location01;

    @Mock
    private Location location10;

    @Mock
    private Location location11;

    @Mock
    private Location location20;

    @Mock
    private Location location21;

    private Set<Location> occupiedLocations;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<List<Location>> dimensions = List.of(
                List.of(location00, location01),
                List.of(location10, location11),
                List.of(location20, location21)
        );
        occupiedLocations = new HashSet<>();
        grid = new Grid(3, 2, dimensions, occupiedLocations);
    }
    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenRowIs0() {
        new Grid(0, 2);
    }

    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenRowIsNegative() {
        new Grid(-1, 2);
    }

    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenColumnIs0() {
        new Grid(1, 0);
    }

    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenColumnIsNegative() {
        new Grid(1, -2);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIs0() {
        grid.seedRandomCells(2, 2, 0);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIsNegative() {
        grid.seedRandomCells(2, 2, -1);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIs100() {
        grid.seedRandomCells(2, 2, 100);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIsGreaterThan100() {
        grid.seedRandomCells(2, 2, 150);
    }

    @Test
    public void testSeedRandomCells_SetsNumberOfHabitableLocations3_WhenSeedPercentageIs50() {
        when(location00.isOccupied()).thenReturn(true);
        when(location01.isOccupied()).thenReturn(true);
        when(location10.isOccupied()).thenReturn(true);
        when(location11.isOccupied()).thenReturn(false);
        when(location20.isOccupied()).thenReturn(false);
        when(location21.isOccupied()).thenReturn(false);

        grid.seedRandomCells(3, 2, 50);

        verify(location00, never()).makeHabitable();
        verify(location01, never()).makeHabitable();
        verify(location10, never()).makeHabitable();
        verify(location11, times(1)).makeHabitable();
        verify(location20, times(1)).makeHabitable();
        verify(location21, times(1)).makeHabitable();
    }

    @Test
    public void testSeedRandomCells_SetsNumberOfHabitableLocations1_WhenSeedPercentageIs30() {
        when(location00.isOccupied()).thenReturn(false);
        when(location01.isOccupied()).thenReturn(false);
        when(location10.isOccupied()).thenReturn(true);
        when(location11.isOccupied()).thenReturn(false);
        when(location20.isOccupied()).thenReturn(false);
        when(location21.isOccupied()).thenReturn(false);

        grid.seedRandomCells(3, 2, 30);

        verify(location00, atMostOnce()).makeHabitable();
        verify(location01, atMostOnce()).makeHabitable();
        verify(location10, never()).makeHabitable();
        verify(location11, atMostOnce()).makeHabitable();
        verify(location20, atMostOnce()).makeHabitable();
        verify(location21, atMostOnce()).makeHabitable();
    }

    @Test
    public void testUpdate_CallsUpdateStateOnAllLocations_WhenCalled() {
        grid.update(3, 2);

        verify(location00, times(1)).updateState(occupiedLocations, 3, 2);
        verify(location10, times(1)).updateState(occupiedLocations, 3, 2);
        verify(location01, times(1)).updateState(occupiedLocations, 3, 2);
        verify(location11, times(1)).updateState(occupiedLocations, 3, 2);
        verify(location20, times(1)).updateState(occupiedLocations, 3, 2);
        verify(location21, times(1)).updateState(occupiedLocations, 3, 2);
    }

    @Test
    public void testAreAllCellsDead_ReturnsTrue_WhenAllLocationsAreNotHabitable() {
        when(location00.isOccupied()).thenReturn(false);
        when(location01.isOccupied()).thenReturn(false);
        when(location10.isOccupied()).thenReturn(false);
        when(location11.isOccupied()).thenReturn(false);
        when(location20.isOccupied()).thenReturn(false);
        when(location21.isOccupied()).thenReturn(false);

        occupiedLocations.clear();

        assertTrue(grid.areAllCellsDead());
    }

    @Test
    public void testAreAllCellsDead_ReturnsFalse_WhenAnyLocationIsHabitable() {
        when(location00.isOccupied()).thenReturn(false);
        when(location01.isOccupied()).thenReturn(true);
        when(location10.isOccupied()).thenReturn(false);
        when(location11.isOccupied()).thenReturn(false);
        when(location20.isOccupied()).thenReturn(false);
        when(location21.isOccupied()).thenReturn(false);

        occupiedLocations.clear();
        occupiedLocations.add(location01);

        assertFalse(grid.areAllCellsDead());
    }


    @Test
    public void testDisplay_PrintsCorrectRepresentation_WhenAllLocationsAreOccupied() {
        when(location00.isOccupied()).thenReturn(true);
        when(location01.isOccupied()).thenReturn(true);
        when(location10.isOccupied()).thenReturn(true);
        when(location11.isOccupied()).thenReturn(true);
        when(location20.isOccupied()).thenReturn(true);
        when(location21.isOccupied()).thenReturn(true);

        grid.display();

        String expectedOutput = "**\n**\n**\n";
        assertEquals(expectedOutput, grid.getCurrentState());
    }

    @Test
    public void testDisplay_PrintsCorrectRepresentation_WhenNoLocationsAreOccupied() {
        when(location00.isOccupied()).thenReturn(false);
        when(location01.isOccupied()).thenReturn(false);
        when(location10.isOccupied()).thenReturn(false);
        when(location11.isOccupied()).thenReturn(false);
        when(location20.isOccupied()).thenReturn(false);
        when(location21.isOccupied()).thenReturn(false);

        grid.display();

        String expectedOutput = "__\n__\n__\n";
        assertEquals(expectedOutput, grid.getCurrentState());
    }

    @Test
    public void testDisplay_PrintsCorrectRepresentation_WhenSomeLocationsAreOccupied() {
        when(location00.isOccupied()).thenReturn(true);
        when(location01.isOccupied()).thenReturn(false);
        when(location10.isOccupied()).thenReturn(true);
        when(location11.isOccupied()).thenReturn(false);
        when(location20.isOccupied()).thenReturn(true);
        when(location21.isOccupied()).thenReturn(false);

        grid.display();

        String expectedOutput = "*_\n*_\n*_\n";
        assertEquals(expectedOutput, grid.getCurrentState());
    }

    @Test(expected = NeverEndingCycleException.class)
    public void testNeverEndingCycleException_ThrowsException_WhenSameStateIsRepeatedMoreThan3Times() {
        grid.update(4, 4);
        grid.update(4, 4);
        grid.update(4, 4);
        grid.update(4, 4);
    }
}