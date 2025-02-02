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
    public void testStartGameDisplayGrid() {
        grid = spy(new Grid());
        gameRunner = spy(new GameRunner(grid, scanner));

        gameRunner.startGame(4, 4, 60);
        grid.display();

        verify(grid, atLeastOnce()).display();
    }

    @Test
    public void testStartGame_exitsWhenAllCellsAreDead() {
        grid = spy(new Grid());
        gameRunner = spy(new GameRunner(grid, scanner));

        doReturn(true).when(grid).isAllDead();
        gameRunner.startGame(4, 4, 60);

        verify(grid, atLeastOnce()).display();
        verify(grid, times(1)).isAllDead();
    }

    @Test
    public void testStartGame_exitsOnUserQuit() {
        grid = spy(new Grid());
        gameRunner = spy(new GameRunner(grid, scanner));

        doReturn("q").when(scanner).nextLine();

        gameRunner.startGame(4, 4, 60);

        verify(grid, atLeastOnce()).display();
    }

    @Test
    public void testStartGame_updateGrid() {
        grid = spy(new Grid());
        gameRunner = spy(new GameRunner(grid, scanner));

        gameRunner.startGame(4, 4, 60);
        grid.update();

        verify(grid, atLeastOnce()).display();
        verify(grid, atLeastOnce()).update();
    }
}
