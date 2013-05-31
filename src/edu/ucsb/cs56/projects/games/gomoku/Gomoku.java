package edu.ucsb.cs56.projects.games.gomoku;

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
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
   * Gomoku class that display a board and allow two players to play
   * against each other
   * Edited by Eric Huang for project 02, Spring 2013
   
   @version CS56, Sprint 2013
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
	private int[][] grid;
	
	//Colors
	private final Color player1Color = new Color(0,200,0);
	private final Color player2Color = new Color(0,0,200);
	private final Color emptyColor = new Color(200,200,200);
	
	
	/** 
	 * constructor that creates a board, set the grid empty, set
	 * the initial color
	 */
	public Gomoku(){
		
		super();
		
		//	General variables
		gomokuTask = new gomokuTimerTask();
		boardSize = new Point(70,40);
		grid = new int[boardSize.x][boardSize.y];
		for(int x = 0; x<boardSize.x;x++){
			for(int y = 0;y<boardSize.y;y++){
				grid[x][y] = 0;
			}
		}
		tileSize = 20;
		int screenHeight = boardSize.y*tileSize;
		int screenWidth = boardSize.x*tileSize;
		screen = new Rectangle(0, 0, screenWidth, screenHeight);
		frame = new JFrame("Gomoku");
		random = new Random();
		
		//Game
		currentColor = 1;
		
		//Add listeners that keep track of the mouse 
		addMouseListener(this);
	}
	
	/** 
	 * repaint the board
	 */
	class gomokuTimerTask extends TimerTask{ 
		//	Main loop, done every iteration.
		public void run(){
			
			//Repaint
			frame.repaint();
		}
	}
	
	/** 
	 * the method to set the color of each grid and then switches player
	 */
	public void paintComponent(Graphics g){
		
		for(int x = 0;x<boardSize.x;x++){
			for(int y = 0; y<boardSize.y;y++){
				
				if(grid[x][y] == 1){
					g.setColor(player1Color);
				}else if(grid[x][y]== 2){
					g.setColor(player2Color);
				}else{
					g.setColor(emptyColor);
				}
				g.fillOval(x*tileSize, y*tileSize, tileSize, tileSize);
			}
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
