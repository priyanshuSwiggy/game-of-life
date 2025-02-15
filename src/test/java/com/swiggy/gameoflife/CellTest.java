package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    private Cell aliveCell;
    private Cell deadCell;

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
    public void testRevive_ChangesCellToAlive() {
        deadCell.revive();

        assertTrue(deadCell.isAlive());
    }

    @Test
    public void testKill_ChangesCellToDead() {
        aliveCell.kill();
        assertFalse(aliveCell.isAlive());
    }

    @Test
    public void testNextState_KillsAliveCell_WithZeroAliveNeighbors() {
        Cell nextState = aliveCell.nextState(0);
        assertFalse(nextState.isAlive());
    }

    @Test
    public void testNextState_KillsAliveCell_WithOneAliveNeighbor() {
        Cell nextState = aliveCell.nextState(1);
        assertFalse(nextState.isAlive());
    }

    @Test
    public void testNextState_KillsAliveCell_WithFourAliveNeighbors() {
        Cell nextState = aliveCell.nextState(4);
        assertFalse(nextState.isAlive());
    }

    @Test
    public void testNextState_KillsAliveCell_WithFiveAliveNeighbors() {
        Cell nextState = aliveCell.nextState(5);
        assertFalse(nextState.isAlive());
    }

    @Test
    public void testNextState_RevivesDeadCell_WithExactlyThreeAliveNeighbors() {
        Cell nextState = deadCell.nextState(3);
        assertTrue(nextState.isAlive());
    }

    @Test
    public void testNextState_DoesNotChangeAliveCell_WithTwoAliveNeighbors() {
        Cell nextState = aliveCell.nextState(2);
        assertTrue(nextState.isAlive());
    }

    @Test
    public void testNextState_DoesNotChangeAliveCell_WithThreeAliveNeighbors() {
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