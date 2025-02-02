package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CellTest {

    private Cell aliveCell;
    private Cell deadCell;
    private final Cell initialCell;
    private final int aliveNeighbors;
    private final boolean expectedAliveState;

    public CellTest(Cell initialCell, int aliveNeighbors, boolean expectedAliveState) {
        this.initialCell = initialCell;
        this.aliveNeighbors = aliveNeighbors;
        this.expectedAliveState = expectedAliveState;
    }

    @Before
    public void setUp() {
        aliveCell = new Cell(CellState.ALIVE);
        deadCell = new Cell(CellState.DEAD);
    }

    @Test
    public void testIsAlive_ReturnsTrue_WhenCellIsAlive() {
        assertTrue(aliveCell.isAlive());
    }

    @Test
    public void testIsAlive_ReturnsFalse_WhenCellIsDead() {
        assertFalse(deadCell.isAlive());
    }

    @Test
    public void testMakeAlive_ChangesCellToAlive() {
        deadCell.makeAlive();
        assertTrue(deadCell.isAlive());
    }

    @Test
    public void testKill_ChangesCellToDead() {
        aliveCell.kill();
        assertFalse(aliveCell.isAlive());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Cell(CellState.ALIVE), 0, false},
                {new Cell(CellState.ALIVE), 1, false},
                {new Cell(CellState.ALIVE), 4, false},
                {new Cell(CellState.ALIVE), 5, false}
        });
    }

    @Test
    public void testNextState_KillsCellWithInvalidAliveNeighbors() {
        Cell nextState = initialCell.nextState(aliveNeighbors);
        assertEquals(expectedAliveState, nextState.isAlive());
    }

    @Test
    public void testNextState_RevivesCellWithExactlyThreeAliveNeighbors() {
        Cell nextState = deadCell.nextState(3);
        assertTrue(nextState.isAlive());
    }

    @Test
    public void testNextState_DoesNotChangeCellWithTwoAliveNeighbors() {
        Cell nextState = aliveCell.nextState(2);
        assertTrue(nextState.isAlive());
    }

    @Test
    public void testNextState_DoesNotChangeCellWithThreeAliveNeighbors() {
        Cell nextState = aliveCell.nextState(3);
        assertTrue(nextState.isAlive());
    }

    @Test
    public void testPrintCell_ReturnsSymbolForAliveCell() {
        assertEquals(CellState.ALIVE.getSymbol(), aliveCell.printCell());
    }

    @Test
    public void testPrintCell_ReturnsSymbolForDeadCell() {
        assertEquals(CellState.DEAD.getSymbol(), deadCell.printCell());
    }
}