package edu.ucsb.cs56.projects.games.gomoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class AllIDoIsWinTest {

    /*
    public static int [][] grid = new int[19][19];
    
    public static int[][] resetGrid() {
        for (int i=0; i < 19; i++) {
  	    for (int j=0; j < 19; j++) {
	        grid[i][j] = 0;
	    }
        }
    }
    */

    @Test
    public void testHorizontalPlayerOneWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[i][0] = 1;
	}
	int x = test.checkHorizontalWin(test.grid);
	assertEquals(1, x);
    }
    
}
