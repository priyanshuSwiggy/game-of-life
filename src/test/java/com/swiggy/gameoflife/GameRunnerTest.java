package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


public class GameRunnerTest {

    @InjectMocks
    private GameRunner gameRunner;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStartGame() {
        gameRunner = spy(new GameRunner());

        gameRunner.startGame(3, 3, 60);

        verify(gameRunner).startGame(3, 3, 60);
    }
}
