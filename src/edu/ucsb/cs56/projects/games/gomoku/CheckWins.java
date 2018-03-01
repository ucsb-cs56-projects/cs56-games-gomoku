package edu.ucsb.cs56.projects.games.gomoku;

public class CheckWins {

	/**
	 * Runs all of the other test methods
	 * 
	 * @param grid
	 *            - the board
	 * @param gameType
	 *            - the difference between standard and original
	 * @return is which player wins
	 */
	public static int checkForWin(int[][] grid, boolean gameType) {
		int win = 0;
		win = checkHorizontalWin(grid, gameType);
		if (win != 0) {
			return win;
		}
		win = checkVerticalWin(grid, gameType);
		if (win != 0) {
			return win;
		}
		win = checkDownwardsDiagonals(grid, gameType);
		if (win != 0) {
			return win;
		}
		win = checkUpwardsDiagonals(grid, gameType);
		if (win != 0) {
			return win;
		}
		return 0;
	}

	/**
	 * Checks if someone has won by placing 5 stones in a row diagonally upwards
	 * from left to right
	 * 
	 * @param boardToCheck
	 *            to check for a winning player
	 * @param playStandard
	 *            - check if standard or freestyle
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public static int checkUpwardsDiagonals(int[][] boardToCheck, boolean playStandard) {
		return checkDownwardsDiagonals(flipBoardVertically(boardToCheck), playStandard);

	}

	/**
	 * Flips the board vertically (mirrors it)
	 * 
	 * @param boardToCheck
	 *            the board that shall be flipped
	 * @return flipped board
	 */
	private static int[][] flipBoardVertically(int[][] boardToCheck) {
		int[][] boardToReturn = new int[boardToCheck.length][boardToCheck[0].length];
		for (int x = 0; x < boardToCheck.length; x++) {
			for (int y = 0; y < boardToCheck[0].length; y++) {
				boardToReturn[x][y] = boardToCheck[x][boardToCheck[0].length - 1 - y];
			}
		}
		return boardToReturn;
	}

	/**
	 * Checks if someone has won by placing 5 stones in a row diagonally
	 * downwards from left to right
	 * 
	 * @param boardToCheck
	 *            the board to check for a winning player
	 * @param playStandard
	 *            - checks version of game
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public static int checkDownwardsDiagonals(int[][] boardToCheck, boolean playStandard) {
		// First check diagonally from left to right downwards
		// Middle and lower diagonals
		int win = checkLowerDownwardsDiagonals(boardToCheck, playStandard);
		if (win != 0) {
			return win;
		}
		// Check upper diagonals
		win = checkLowerDownwardsDiagonals(flipBoardDiagonally(boardToCheck), playStandard);
		if (win != 0) {
			return win;
		}
		return 0;
	}

	/**
	 * Checks if someone has won by placing 5 stones in a row diagonally
	 * downwards from left to right in any of the lower diagonals (below the one
	 * that starts at (0,0)).
	 * 
	 * @param boardToCheck
	 *            the board to check for a winning player
	 * @param playStandard
	 *            - checks version of game
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public static int checkLowerDownwardsDiagonals(int[][] boardToCheck, boolean playStandard) {
		// First check diagonally from left to right downwards
		// Middle and lower diagonals
		for (int diagonalNo = 0; diagonalNo < boardToCheck[0].length; diagonalNo++) {
			int y = diagonalNo;
			int x = 0;
			int maxInARow = 0;
			int lastColor = 0;
			int potentialWinner = 0;
			while (x < boardToCheck.length && y < boardToCheck[0].length) {
				if (boardToCheck[x][y] == lastColor && lastColor != 0) {
					// Same as last color, and not empty
					maxInARow++;
				} else if (boardToCheck[x][y] != lastColor && boardToCheck[x][y] != 0) {
					if (maxInARow == 5 && playStandard) {
						// Standard Gomoku, which requires EXACTLY five in a
						// row.
						return lastColor;
					}
					// Not same as last color, and not empty
					maxInARow = 1;
				} else {

					// Reset
					maxInARow = 0;
				}
				// Check for 5 in a row
				// Freestyle Gomoku, wich allows five or more in a row.
				// OR Standard Gomoku, which requires EXACTLY five in a
				// row.
				if (maxInARow == 5 && playStandard) {
				    if (x < 15 && y < 15){
					if( x == 14 || y == 14){
					    return lastColor;
					} else if( boardToCheck[x + 1][y + 1] != lastColor){
						return lastColor;
					}
				    }
					
				}

				// Update lastcolor
				lastColor = boardToCheck[x][y];

				x++;
				y++;
			}
			maxInARow = 0;
			lastColor = 0;
		}
		return 0;
	}

	/**
	 * Checks if someone has won by placing 5 stones in a horizontal row
	 * 
	 * @param boardToCheck
	 *            the board to check for a winning player
	 * @param playStandard
	 *            - checks version of game
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public static int checkHorizontalWin(int[][] boardToCheck, boolean playStandard) {
		// Horisontal
		int lastColor = 0;
		int maxInARow = 0;
		int potentialWinner = 0;
		for (int y = 0; y < boardToCheck[0].length; y++) {
			for (int x = 0; x < boardToCheck.length; x++) {
				if (boardToCheck[x][y] == lastColor && lastColor != 0) {
					// Same as last color, and not empty
					maxInARow++;
				} else if (boardToCheck[x][y] != lastColor && boardToCheck[x][y] != 0) {
					if (maxInARow == 5 && playStandard) {
						// Standard Gomoku, which requires EXACTLY five in a
						// row.
						return lastColor;
					}
					// Not same as last color, and not empty.
					maxInARow = 1;
				} else {
					// Reset
					maxInARow = 0;
				}
				// Check for 5 in a row
				// Freestyle Gomoku, wich allows five or more in a row.
				// OR Standard Gomoku, which requires EXACTLY five in a
				// row.
				if (maxInARow == 5 && playStandard) {
				    if (x < 15){
					if(x == 14){
					    return lastColor;
					} else if (boardToCheck[x + 1][y] != lastColor){
						return lastColor;
					}
				    }
					
				}

				// Update lastcolor
				lastColor = boardToCheck[x][y];
			}
			maxInARow = 0;
			lastColor = 0;
		}
		return 0;
	}

	/**
	 * Checks if someone has won by placing 5 stones in a vertical row
	 * 
	 * @param boardToTest
	 *            the board to test for a winning player
	 * @param playStandard
	 *            - checks version of game
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public static int checkVerticalWin(int[][] boardToTest, boolean playStandard) {
		return checkHorizontalWin(flipBoardDiagonally(boardToTest), playStandard);
	}

	/**
	 * Flips a board diagonally (i.e. (1,2) becomes (2,1))
	 * 
	 * @param boardToFlip
	 *            - the board to be flipped
	 * @return flipped board
	 */
	private static int[][] flipBoardDiagonally(int[][] boardToFlip) {
		int[][] boardToReturn = new int[boardToFlip[0].length][boardToFlip.length];
		for (int x = 0; x < boardToFlip.length; x++) {
			for (int y = 0; y < boardToFlip[0].length; y++) {
				boardToReturn[y][x] = boardToFlip[x][y];
			}
		}
		return boardToReturn;
	}

}
