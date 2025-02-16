package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, Boolean> occupiedLocations;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<List<Location>> dimensions = List.of(
                List.of(location00, location01),
                List.of(location10, location11),
                List.of(location20, location21)
        );
        occupiedLocations = new HashMap<>();
        grid = new Grid(3, 2, dimensions, occupiedLocations);
        System.setOut(new PrintStream(outContent));
    }
    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenRowIsNonPositive() {
        new Grid(-1, 2);
    }

    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenColumnIsNonPositive() {
        new Grid(1, -2);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIsLessThan0() {
        grid.seedRandomCells(2, 2, -1);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIsGreaterThan100() {
        grid.seedRandomCells(2, 2, 150);
    }

    @Test
    public void testSeedRandomCells_SetsCorrectNumberOfHabitableLocations_WhenSeedPercentageIs50() {
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
    public void testSeedRandomCells_SetsCorrectNumberOfHabitableLocations_WhenSeedPercentageIs30() {
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
        when(location00.isHabitable()).thenReturn(false);
        when(location01.isHabitable()).thenReturn(false);
        when(location10.isHabitable()).thenReturn(false);
        when(location11.isHabitable()).thenReturn(false);
        when(location20.isHabitable()).thenReturn(false);
        when(location21.isHabitable()).thenReturn(false);

        assertTrue(grid.areAllCellsDead());
    }

    @Test
    public void testAreAllCellsDead_ReturnsFalse_WhenAnyLocationIsHabitable() {
        when(location00.isHabitable()).thenReturn(false);
        when(location01.isHabitable()).thenReturn(true);
        when(location10.isHabitable()).thenReturn(false);
        when(location11.isHabitable()).thenReturn(false);
        when(location20.isHabitable()).thenReturn(false);
        when(location21.isHabitable()).thenReturn(false);

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
        assertEquals(expectedOutput, outContent.toString());
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
        assertEquals(expectedOutput, outContent.toString());
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
        assertEquals(expectedOutput, outContent.toString());
    }
}