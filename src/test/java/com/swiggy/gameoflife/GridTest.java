package com.swiggy.gameoflife;

import com.swiggy.gameoflife.exception.InvalidGridDimensionsException;
import com.swiggy.gameoflife.exception.InvalidSeedPercentageException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

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

    @Mock
    private Cell cell20;

    @Mock
    private Cell cell21;

    private Grid grid;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        grid = new Grid(3, 2);
        List<List<Cell>> cells = Arrays.asList(
                Arrays.asList(cell00, cell01),
                Arrays.asList(cell10, cell11),
                Arrays.asList(cell20, cell21)
        );
        grid = new Grid(3, 2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                grid.cells.get(i).set(j, cells.get(i).get(j));
            }
        }
    }

    @Test
    public void testDisplay_ShowsMixedCells() {
        grid.display();

        verify(cell00, times(1)).printCell();
        verify(cell01, times(1)).printCell();
        verify(cell10, times(1)).printCell();
        verify(cell11, times(1)).printCell();
    }

    @Test
    public void testAreAllCellsDead_ReturnsTrue_WhenAllCellsAreDead() {
        when(cell00.isAlive()).thenReturn(false);
        when(cell01.isAlive()).thenReturn(false);
        when(cell10.isAlive()).thenReturn(false);
        when(cell11.isAlive()).thenReturn(false);

        assertTrue(grid.areAllCellsDead());
    }

    @Test
    public void testAreAllCellsDead_ReturnsFalse_WhenAnyCellIsAlive() {
        when(cell00.isAlive()).thenReturn(false);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(false);
        when(cell11.isAlive()).thenReturn(false);

        assertFalse(grid.areAllCellsDead());
    }

    @Test
    public void testSeedRandomCells_SetsCorrectNumberOfAliveCells() {
        grid.seedRandomCells(2, 2, 50);

        verify(cell00, atMost(1)).makeAlive();
        verify(cell01, atMost(1)).makeAlive();
        verify(cell10, atMost(1)).makeAlive();
        verify(cell11, atMost(1)).makeAlive();
    }

    @Test
    public void testSeedRandomCells_DoesNotExceedGridBounds() {
        grid.seedRandomCells(2, 2, 100);

        verify(cell00, atMost(1)).makeAlive();
        verify(cell01, atMost(1)).makeAlive();
        verify(cell10, atMost(1)).makeAlive();
        verify(cell11, atMost(1)).makeAlive();
    }

    @Test
    public void testSeedRandomCells_HandlesZeroPercentage() {
        grid.seedRandomCells(2, 2, 0);

        verify(cell00, never()).makeAlive();
        verify(cell01, never()).makeAlive();
        verify(cell10, never()).makeAlive();
        verify(cell11, never()).makeAlive();
    }

    @Test
    public void testSeedRandomCells_HandlesFullPercentage() {
        grid.seedRandomCells(2, 2, 100);

        verify(cell00, times(1)).makeAlive();
        verify(cell01, times(1)).makeAlive();
        verify(cell10, times(1)).makeAlive();
        verify(cell11, times(1)).makeAlive();
    }

    @Test
    public void testUpdate_DoesNotChangeCellWithTwoAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(false);

        grid.update();

        verify(cell00, never()).kill();
        verify(cell00, never()).makeAlive();
    }

    @Test
    public void testUpdate_DoesNotChangeCellWithThreeAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(true);

        grid.update();

        verify(cell00, never()).kill();
        verify(cell00, never()).makeAlive();
    }

    @Test
    public void testUpdate_KillsCellWithOneOrFewerAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(false);
        when(cell10.isAlive()).thenReturn(false);
        when(cell11.isAlive()).thenReturn(false);

        // Mock the next state for cell00 to be DEAD (killed).
        when(cell00.nextState(0)).thenReturn(new Cell(CellState.DEAD));

        grid.update();

        verify(cell00, times(1)).nextState(0);
        verify(cell00, never()).makeAlive();
    }

    @Test
    public void testUpdate_KillsCellWithFourOrMoreAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(true);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(true);
        when(cell20.isAlive()).thenReturn(true);
        when(cell21.isAlive()).thenReturn(true);

        when(cell11.nextState(5)).thenReturn(new Cell(CellState.DEAD));

        grid.update();

        verify(cell11, times(1)).nextState(5);
        verify(cell11, never()).makeAlive();
    }

    @Test
    public void testUpdate_MakesCellAliveWithExactlyThreeAliveNeighbors() {
        when(cell00.isAlive()).thenReturn(false);
        when(cell01.isAlive()).thenReturn(true);
        when(cell10.isAlive()).thenReturn(true);
        when(cell11.isAlive()).thenReturn(true);

        when(cell00.nextState(3)).thenReturn(new Cell(CellState.ALIVE));

        grid.update();

        verify(cell00, times(1)).nextState(3);
        verify(cell00, never()).kill();
    }

    @Test(expected = InvalidGridDimensionsException.class)
    public void testInvalidGridDimensionsException_ThrowsException_WhenDimensionsAreInvalid() {
        new Grid(-1, 2);
    }

    @Test(expected = InvalidSeedPercentageException.class)
    public void testInvalidSeedPercentageException_ThrowsException_WhenSeedPercentageIsInvalid() {
        grid.seedRandomCells(2, 2, 150);
    }

}