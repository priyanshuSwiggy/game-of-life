package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

    private Cell[][] cells;

    private Grid grid;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cells = new Cell[][]{{cell00, cell01}, {cell10, cell11}};
        grid = new Grid(2, 2, cells);
    }

    @Test
    public void testDisplay_showsMixedCells() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(false);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(false);

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
}