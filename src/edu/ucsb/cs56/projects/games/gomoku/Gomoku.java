package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
   * Gomoku class that display a board and allow two players to play
   * against each other
   * Edited by Eric Huang for project 02, Spring 2013
   
   @version CS56, Sprint 2013
*/
/**
 *edited for CS56 F16
 *@author Madhu Kannan, Colin Garrett
 */
public class Gomoku extends JPanel implements MouseListener
{
	
	//General variables, such as screen size etc.
	private Rectangle screen;	
	public JFrame frame;
	private Point boardSize;
	private int tileSize;
	public gomokuTimerTask gomokuTask;
	private Random random;
	
	//Game
	private int currentColor;	//Either 1 or 2
	public int[][] grid;
	public Timer mainProgramTimer;
	public JFrame newFrame;	
	public boolean playStandard = true; //Standard Gomoku requires exactly 5. Freestyle allows 5 or more.This variable is set from the home screen's check box, in the Viewer class. 

	//Colors
	private Color player1Color = new Color(0,200,0);
	private Color player2Color = new Color(0,0,200);
	private Color emptyColor = new Color(200,200,200);
	
	/** 
	 * constructor that creates a board, set the grid empty, set
	 * the initial color
	 */
	public Gomoku(){
		
		super();
		
		//	General variables
		gomokuTask = new gomokuTimerTask();
		boardSize = new Point(19,19);
		grid = new int[boardSize.x][boardSize.y];
		for(int x = 0; x<boardSize.x;x++){
			for(int y = 0;y<boardSize.y;y++){
				grid[x][y] = 0;
			}
		}
		tileSize = 20;
		int screenHeight = boardSize.y*tileSize + tileSize;
		int screenWidth = boardSize.x*tileSize + 500;
		screen = new Rectangle(0, 0, screenWidth, screenHeight);
		frame = new JFrame("Gomoku");
		random = new Random();
		
		//Game
		currentColor = 1;		

		//Add listeners that keep track of the mouse 
		addMouseListener(this);
		newFrame = new JFrame();
	}
	
	/** 
	 * repaint the board
	 */
	class gomokuTimerTask extends TimerTask{ 
		//	Main loop, done every iteration.
		public void run(){
			int win = checkForWin();
			if(win!=0){
				JLabel theWinner = new JLabel("something is wrong with the code");
				System.out.println("Player "+win+" has won");
				if(win == 1){
					theWinner = new JLabel("Congratulations, player one, you won!");;
				}
				if(win == 2){
					theWinner = new JLabel("Congratulations, player two, you won!");
				}
				JLabel text = new JLabel("Do you want to play again?");
				JButton playAgainButton = new JButton("Yes");
				playAgainButton.addActionListener(new PlayAgainListener());
				JButton returnToTitleScreenButton = new JButton("No");
			        returnToTitleScreenButton.addActionListener(new ReturnToTitleScreenListener());
				JPanel content = new JPanel();
			        content.add(theWinner);
				content.add(text);
				content.add(playAgainButton);
				content.add(returnToTitleScreenButton);
				newFrame.getContentPane().add(content);
				newFrame.setSize(300, 300);
				newFrame.setVisible(true);
				cancel();
				mainProgramTimer.cancel();
			}
			//Repaint
			frame.repaint();
		}

		class ReturnToTitleScreenListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				newFrame.dispose();
			}

		}

		class PlayAgainListener extends GameScreenListener {

			public void actionPerformed(ActionEvent event) {

				frame.dispose();
				newFrame.dispose();
				super.actionPerformed(event);

			}

		}

	}



	private int checkForWin(){
		int win = 0;
		win = checkHorizontalWin(grid);
		if(win!=0){
			return win;
		}
		win = checkVerticalWin(grid);
		if(win!=0){
			return win;
		}
		win = checkDownwardsDiagonals(grid);
		if(win!=0){
			return win;
		}
		win = checkUpwardsDiagonals(grid);
		if(win!=0){
			return win;
		}
		return 0;
	}
/**
	 * Checks if someone has won by placing 5 stones in a row diagonally upwards from left to right
	 * @param the board to check for a winning player
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public int checkUpwardsDiagonals(int[][] boardToCheck){
		return checkDownwardsDiagonals(flipBoardVertically(boardToCheck));
		
	}

/**
	 * Flips the board vertically (mirrors it) 
	 * @param the board that shall be flipped
	 * @return flipped board
	 */
	private int[][] flipBoardVertically(int[][] boardToCheck){
		int[][] boardToReturn = new int[boardToCheck.length][boardToCheck[0].length];
		for(int x = 0;x<boardToCheck.length;x++){
			for(int y = 0;y<boardToCheck[0].length;y++){
				boardToReturn[x][y] = boardToCheck[x][boardToCheck[0].length-1-y];
			}
		}
		return boardToReturn;
	}

/**
	 * Checks if someone has won by placing 5 stones in a row diagonally downwards from left to right
	 * @param the board to check for a winning player
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public int checkDownwardsDiagonals(int[][] boardToCheck){
		//First check diagonally from left to right downwards
		//Middle and lower diagonals
		int win = checkLowerDownwardsDiagonals(boardToCheck);
		if(win!=0){
			return win;
		}
		//Check upper diagonals
		win = checkLowerDownwardsDiagonals(flipBoardDiagonally(boardToCheck));
		if(win!=0){
			return win;
		}
		return 0;
	}

/**
	 * Checks if someone has won by placing 5 stones in a row diagonally downwards
	 * from left to right in any of the lower diagonals (below the one that starts
	 * at (0,0)).
	 * @param the board to check for a winning player
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public int checkLowerDownwardsDiagonals(int[][] boardToCheck){
		//First check diagonally from left to right downwards
		//Middle and lower diagonals
		for(int diagonalNo = 0;diagonalNo<boardToCheck[0].length;diagonalNo++){
			int y = diagonalNo;
			int x = 0;
			int maxInARow = 0;
			int lastColor = 0;
			int potentialWinner = 0;
			while(x<boardToCheck.length&&y<boardToCheck[0].length){
				if(boardToCheck[x][y]==lastColor&&lastColor!=0){
					//Same as last color, and not empty
					maxInARow++;
				}else if(boardToCheck[x][y]!=lastColor&&boardToCheck[x][y]!=0){
				    if(maxInARow == 5 && playStandard){
					//Standard Gomoku, which requires EXACTLY five in a row.
					return lastColor;
				    }
				    //Not same as last color, and not empty
					maxInARow = 1;
				}else{
				    if(maxInARow == 5 && playStandard){
					//Standard Gomoku, which requires EXACTLY five in a row.
					return lastColor;
				    }
					//Reset
					maxInARow = 0;
				}
				//Check for 5 in a row
				if(maxInARow >= 5 && !playStandard){
				    	//Freestyle Gomoku, wich allows five or more in a row.
					return lastColor;
				}
				//Update lastcolor
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
	 * @param the board to check for a winning player
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public int checkHorizontalWin(int[][] boardToCheck){
		//Horisontal
		int lastColor = 0;
		int maxInARow = 0;
		int potentialWinner = 0;
		for(int y = 0;y<boardToCheck[0].length;y++){
			for(int x = 0;x<boardToCheck.length;x++){
				if(boardToCheck[x][y]==lastColor&&lastColor!=0){
					//Same as last color, and not empty
					maxInARow++;
				}else if(boardToCheck[x][y]!=lastColor&&boardToCheck[x][y]!=0){
				    if(maxInARow == 5 && playStandard){
					//Standard Gomoku, which requires EXACTLY five in a row.
					return lastColor;
				    }
		       			//Not same as last color, and not empty.
					maxInARow = 1;
				}else{

				    if(maxInARow == 5 && playStandard){
					//Standard Gomoku, which requires EXACTLY five in a row.
					return lastColor;
				    }
					//Reset
					maxInARow = 0;
				}
				//Check for 5 in a row
				if(maxInARow >= 5 && !playStandard){
				    	//Freestyle Gomoku, wich allows five or more in a row.
					return lastColor;
				}
				//Update lastcolor
				lastColor = boardToCheck[x][y];
			}
			maxInARow = 0;
			lastColor = 0;
		}
		return 0;
	}

/**
	 * Checks if someone has won by placing 5 stones in a vertical row
	 * @param the board to check for a winning player
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	public int checkVerticalWin(int[][] boardToTest){
		return checkHorizontalWin(flipBoardDiagonally(boardToTest));
	}
	
	/**
	 * Flips a board diagonally (i.e. (1,2) becomes (2,1))
	 * @param the board to be flipped
	 * @return flipped board
	 */
	private int[][] flipBoardDiagonally(int[][] boardToFlip){
		int[][] boardToReturn = new int[boardToFlip[0].length][boardToFlip.length];
		for(int x=0;x<boardToFlip.length;x++){
			for(int y=0;y<boardToFlip[0].length;y++){
				boardToReturn[y][x] = boardToFlip[x][y];
			}
		}
		return boardToReturn;
	}

	/** 
	 * the method to set the color of each grid and then switches player
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for(int x = 0;x<boardSize.x;x++){
			for(int y = 0; y<boardSize.y;y++){
				if(grid[x][y] == 1){
					g2.setColor(player1Color);
				}else if(grid[x][y]== 2){
					g2.setColor(player2Color);
				}else{
					g2.setColor(emptyColor);
				}
				g2.fillOval(x*tileSize, y*tileSize, tileSize, tileSize);
			}
		}
		if(getCurrentColor() == 1) {
			Stroke thick = new BasicStroke (4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			g2.setStroke(thick);
			g2.setColor(player1Color);
			g2.drawString("Player one, it is your turn. You are green.", boardSize.x * tileSize + 10, boardSize.y * tileSize / 2 );
		}
		else if (getCurrentColor() == 2) {
			g2.setColor(player2Color);
			g2.drawString("Player two, it is your turn. You are blue.", boardSize.x * tileSize + 10, boardSize.y * tileSize / 2);
		}
	}
	
	//	All functions need to be written (even if they are empty) 
	//  in order to comply with the MouseListener interface
	public void mouseClicked(MouseEvent mouse){
		Controller c = new Controller(this);
		c.coordinate(mouse);
		
		setGrid(c.getXCoord(),c.getYCoord() , getCurrentColor());
		
		//Switch player
		if(getCurrentColor() == 1){
			setCurrentColor(2);
		}else{
			setCurrentColor(1);
	}   
}
 
    /** empty for now */
	public void mouseEntered(MouseEvent mouse){ 
	}   
	/** empty for now */
    public void mouseExited(MouseEvent mouse){
    }
    /** empty for now */
    public void mousePressed(MouseEvent mouse){
    }
    /** empty for now */
    public void mouseReleased(MouseEvent mouse){ 
    }
    /** empty for now */
    public void mouseDragged(MouseEvent mouse){	
    }
    /** empty for now */
    public void mouseMoved(MouseEvent mouse){
    }	
	
	/** 
	 * getter for the currentColor
	 * @return the number that represent the color
	 */
	public int getCurrentColor()
	{
		return currentColor;
	}
	
	/**
	 * setter for currentCollar
	 * @param currentColor the new color to replace what is in the grid
	 */
	public void setCurrentColor(int currentColor)
	{
		this.currentColor = currentColor;
	}

	/**
	 * setter for the color in the grid
	 * @param xCoord the x coordinate
	 * @param yCoord the y coordinate
	 * @param newColor the new color desired
	 */
	public void setGrid(int xCoord, int yCoord, int newColor)
	{
		grid[xCoord][yCoord] = newColor;
	}
	
	/** 
	 * getter for tileSize
	 * @return the size of the tile
	 */
	public int getTileSize() 
	{
		return this.tileSize; 
	}
	
	
	/** 
	 * getter for the width of the screen
	 * get the width of the screen and cast it to integer
	 * @return int
	 */
	public int getScreenWidth()
	{
		return (int)(screen.getWidth());
	}
	
	/** 
	 * getter for the width of the screen
	 * get the width of the screen and cast it to integer
	 * @return int
	 */
	public int getScreenHeight()
	{
		return (int)(this.screen.getHeight());
	}
}
