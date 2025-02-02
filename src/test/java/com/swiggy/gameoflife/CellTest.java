package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.junit.Assert.*;

public class CellTest {

    @Mock
    private Cell cell;

    private Cell aliveCell;
    private Cell deadCell;

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

}
