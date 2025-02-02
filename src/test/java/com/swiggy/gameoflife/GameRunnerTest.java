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

    private Cell[][] cell;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cell = new Cell[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cell[i][j] = mock(Cell.class);
            }
        }
    }

    @Test
    public void testStartGameSetSeedPercentage() {
        grid = spy(new Grid(4, 4, cell));
        gameRunner = spy(new GameRunner(grid, scanner));

        gameRunner.startGame(4, 4, 60);
        grid.randomSeeding(4, 4, 60);

        verify(grid, atLeastOnce()).randomSeeding(4, 4, 60);
    }

    @Test
    public void testStartGameDisplayGrid() {
        grid = spy(new Grid(4, 4, cell));
        gameRunner = spy(new GameRunner(grid, scanner));

        gameRunner.startGame(4, 4, 60);
        grid.display();

        verify(grid, atLeastOnce()).display();
    }

    @Test
    public void testStartGame_exitsWhenAllCellsAreDead() {
        grid = spy(new Grid(4, 4, cell));
        gameRunner = spy(new GameRunner(grid, scanner));

        doReturn(true).when(grid).isAllDead();
        gameRunner.startGame(4, 4, 60);

        verify(grid, atLeastOnce()).display();
        verify(grid, times(1)).isAllDead();
    }

    @Test
    public void testStartGame_exitsOnUserQuit() {
        grid = spy(new Grid(4, 4, cell));
        gameRunner = spy(new GameRunner(grid, scanner));

        doReturn("q").when(scanner).nextLine();

        gameRunner.startGame(4, 4, 60);

        verify(grid, atLeastOnce()).display();
    }

    @Test
    public void testStartGame_updateGrid() {
        grid = spy(new Grid(4, 4, cell));
        gameRunner = spy(new GameRunner(grid, scanner));

        gameRunner.startGame(4, 4, 60);
        grid.update();

        verify(grid, atLeastOnce()).display();
        verify(grid, atLeastOnce()).update();
    }
}
