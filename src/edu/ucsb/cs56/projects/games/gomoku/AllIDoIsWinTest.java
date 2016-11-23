package edu.ucsb.cs56.projects.games.gomoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class AllIDoIsWinTest {

/* These tests check the functionality of the check-for-win methods of Gomoku.java. 
 * More specifically, they check the horizontal, vertical, and diagonal win methods. 
 * Those methods make up the core logic of the Gomoku game, and are fundamental to having a working 
 * product. 
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
    @Test
    public void testVerticalPlayerOneWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[0][i] = 1;
	}
	int x = test.checkVerticalWin(test.grid);
	assertEquals(1, x);
    }
    @Test
    public void testLowerDownwardsDiagonalsPlayerOneWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[i][i+1] = 1;
	}
	int x = test.checkLowerDownwardsDiagonals(test.grid);
	assertEquals(1, x);
    }

    @Test
    public void testDownwardsDiagonalsPlayerOneWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[i][i] = 1;
	}
	int x = test.checkDownwardsDiagonals(test.grid);
	assertEquals(1, x);
    }

    @Test
    //specifically, checks upper half of board (any diagonal above 0,0)
    public void testHorizontalPlayerTwoWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[i][0] = 2;
	}
	int x = test.checkHorizontalWin(test.grid);
	assertEquals(2, x);
    }
    @Test
    public void testVerticalPlayerTwoWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[0][i] = 2;
	}
	int x = test.checkVerticalWin(test.grid);
	assertEquals(2, x);
    }
    @Test
    public void testLowerDownwardsDiagonalsPlayerTwoWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[i][i+1] = 2;
	}
	int x = test.checkLowerDownwardsDiagonals(test.grid);
	assertEquals(2, x);
    }

    @Test
    //specifically, checks upper half of the board (any diagonal above 0,0);
    public void testDownwardsDiagonalsPlayerTwoWin() {
	Gomoku test = new Gomoku();
	for (int i=0; i<5; i++) {
	    test.grid[i][i] = 2;
	}
	int x = test.checkDownwardsDiagonals(test.grid);
	assertEquals(2, x);
    }



}
