package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CellTest {

    @Mock
    private Cell cell;

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
    public void testIsAlive_returnsTrueWhenCellIsAlive() {
        assertTrue(aliveCell.isAlive());
    }

    @Test
    public void testIsAlive_returnsFalseWhenCellIsDead() {
        assertFalse(deadCell.isAlive());
    }

    @Test
    public void testMakeAlive_changesCellToAlive() {
        deadCell.makeAlive();
        assertTrue(deadCell.isAlive());
    }

    @Test
    public void testKill_changesCellToDead() {
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
    public void nextState_killsCellWithInvalidAliveNeighbors() {
        Cell nextState = initialCell.nextState(aliveNeighbors);

        assertEquals(expectedAliveState, nextState.isAlive());
    }
    @Test
    public void testNextState_revivesCellWithExactlyThreeAliveNeighbors() {
        Cell nextState = deadCell.nextState(3);

        assertTrue(nextState.isAlive());
    }

    @Test
    public void testNextState_doesNotChangeCellWithTwoAliveNeighbors() {
        Cell nextState = aliveCell.nextState(2);

        assertTrue(nextState.isAlive());
    }

    @Test
    public void testNextState_doesNotChangeCellWithThreeAliveNeighbors() {
        Cell nextState = aliveCell.nextState(3);

        assertTrue(nextState.isAlive());
    }

    @Test
    public void testPrintCell_returnsSymbolForAliveCell() {
        assertEquals(CellState.ALIVE.getSymbol(), aliveCell.printCell());
    }

    @Test
    public void testPrintCell_returnsSymbolForDeadCell() {
        assertEquals(CellState.DEAD.getSymbol(), deadCell.printCell());
    }

}
