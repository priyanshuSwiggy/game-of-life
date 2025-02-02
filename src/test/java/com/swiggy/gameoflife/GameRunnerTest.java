package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


public class GameRunnerTest {

    @InjectMocks
    private GameRunner gameRunner;

    @Mock
    private Grid grid;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStartGameDisplayGrid() {
        grid = spy(new Grid());
        gameRunner = spy(new GameRunner(grid));

        gameRunner.startGame(4, 4, 60);
        grid.display();

        verify(gameRunner, times(1)).startGame(4, 4, 60);
        verify(grid, times(1)).display();
    }
}
