package com.swiggy.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GridTest {

    @InjectMocks
    private Grid grid;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
