package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class GameRunnerTest {

    @InjectMocks
    private GameRunner gameRunner;

    @Mock
    private Grid grid;

    @Mock
    private Scanner scanner;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStartGame_SetSeedPercentage() {
        doNothing().when(grid).seedRandomCells(anyInt(), anyInt(), anyInt());
        when(scanner.nextLine()).thenReturn("q");

        gameRunner.startGame(4, 4, 60);

        verify(grid, times(1)).seedRandomCells(4, 4, 60);
    }

    @Test
    public void testStartGame_DisplayGrid() {
        when(scanner.nextLine()).thenReturn("q");

        gameRunner.startGame(4, 4, 60);

        verify(grid, atLeastOnce()).display();
    }

    @Test
    public void testStartGame_ExitsWhenAllCellsAreDead() {
        when(grid.areAllCellsDead()).thenReturn(true);
        when(scanner.nextLine()).thenReturn("q");

        gameRunner.startGame(4, 4, 60);

        verify(grid, times(1)).areAllCellsDead();
    }

    @Test
    public void testStartGame_ExitsOnUserQuit() {
        when(scanner.nextLine()).thenReturn("q");

        gameRunner.startGame(4, 4, 60);

        verify(scanner, atLeastOnce()).nextLine();
    }

    @Test
    public void testStartGame_UpdateGrid() {
        when(grid.areAllCellsDead()).thenReturn(false);
        when(scanner.nextLine()).thenReturn("q");

        gameRunner.startGame(4, 4, 60);
        grid.update();

        verify(grid, atLeastOnce()).update();
    }
}