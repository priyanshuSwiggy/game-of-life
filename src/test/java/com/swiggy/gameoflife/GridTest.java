package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GridTest {

    @Mock
    private Cell cell00;

    @Mock
    private Cell cell01;

    @Mock
    private Cell cell10;

    @Mock
    private Cell cell11;

    @Mock
    private Cell cell20;

    @Mock
    private Cell cell21;

    private Grid grid;

    private Cell[][] cells;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cells = new Cell[][]{{cell00, cell01}, {cell10, cell11}, {cell20, cell21}};
        grid = new Grid(3, 2, cells);
    }

    @Test
    public void testDisplay_showsMixedCells() {
        grid.display();

        verify(cell00, times(1)).isAlive();
        verify(cell01, times(1)).isAlive();
        verify(cell10, times(1)).isAlive();
        verify(cell11, times(1)).isAlive();
    }

    @Test
    public void testIsAllDead_returnsTrueWhenAllCellsAreDead() {
        when(cell00.isAlive()).thenReturn(false);
        when(cell01.isAlive()).thenReturn(false);
        when(cell10.isAlive()).thenReturn(false);
        when(cell11.isAlive()).thenReturn(false);

        assertTrue(grid.isAllDead());
    }

    @Test
    public void testIsAllDead_returnsFalseWhenAnyCellIsAlive() {
        when(cell00.isAlive()).thenReturn(false);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(false);
        when(cell11.isAlive()).thenReturn(false);

        assertFalse(grid.isAllDead());
    }

    @Test
    public void testRandomSeeding_setsCorrectNumberOfAliveCells() {
        grid.randomSeeding(2, 2, 50);

        verify(cell00, atMost(1)).makeAlive();
        verify(cell01, atMost(1)).makeAlive();
        verify(cell10, atMost(1)).makeAlive();
        verify(cell11, atMost(1)).makeAlive();
    }

    @Test
    public void testRandomSeeding_doesNotExceedGridBounds() {
        grid.randomSeeding(2, 2, 100);

        verify(cell00, atMost(1)).makeAlive();
        verify(cell01, atMost(1)).makeAlive();
        verify(cell10, atMost(1)).makeAlive();
        verify(cell11, atMost(1)).makeAlive();
    }

    @Test
    public void testRandomSeeding_handlesZeroPercentage() {
        grid.randomSeeding(2, 2, 0);

        verify(cell00, never()).makeAlive();
        verify(cell01, never()).makeAlive();
        verify(cell10, never()).makeAlive();
        verify(cell11, never()).makeAlive();
    }

    @Test
    public void testRandomSeeding_handlesFullPercentage() {
        grid.randomSeeding(2, 2, 100);

        verify(cell00, times(1)).makeAlive();
        verify(cell01, times(1)).makeAlive();
        verify(cell10, times(1)).makeAlive();
        verify(cell11, times(1)).makeAlive();
    }

    @Test
    public void update_doesNotChangeCellWithTwoAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(false);

        grid.update();

        verify(cell00, never()).kill();
        verify(cell00, never()).makeAlive();
    }

    @Test
    public void update_doesNotChangeCellWithThreeAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(true);

        grid.update();

        verify(cell00, never()).kill();
        verify(cell00, never()).makeAlive();
    }




}