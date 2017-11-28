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
 * Gomoku is a game where player take turns to place
 * a colored stones on a chess-style board of various
 * sizes. The first person to get five stones of
 * her own color in a row either horizontally,
 * vertically or diagonally wins.
 * 
 * @author Gunnar Weibull
 *
 */

public class GomokuOrig extends JPanel implements MouseListener{
	
	//General variables, such as screen size etc.
	private Rectangle screen;	
	public JFrame frame;
	private Point boardSize;
	private int tileSize;
	private gomokuTimerTask gomokuTask;
	private Random random;
	private boolean playStandard = true;
	
	//Game
	private int currentColor;	//Either 1 or 2
	private int[][] board;
	
	//Colors
	private Color player1Color = new Color(0,200,0);
	private Color player2Color = new Color(0,0,200);
	private Color emptyColor = new Color(200,200,200);
	
	/**
	 * Constructs and starts a game of Gomoku
	 */
	
	public GomokuOrig(){
		
		super();
		
		//	General variables
		gomokuTask = new gomokuTimerTask();
		boardSize = new Point(70,40);
		tileSize = 20;
		int screenHeigth = boardSize.y*tileSize;
		int screenWidth = boardSize.x*tileSize;
		screen = new Rectangle(0, 0, screenWidth, screenHeigth);
		frame = new JFrame("GomokuOrig");
		random = new Random();
		
		//Game
		currentColor = 1;
		board = new int[boardSize.x][boardSize.y];
		for(int x = 0; x<boardSize.x;x++){
			for(int y = 0;y<boardSize.y;y++){
				board[x][y] = 0;	//The board is empty per default
			}
		}

		//Add listeners that keep track of the mouse 
		addMouseListener(this);
	}
	
	/**
	 * Runs during the course of the game.
	 * 
	 *@author gunnar
	 */
	
	class gomokuTimerTask extends TimerTask{ 
		//	Main loop, done every iteration.
		public void run(){
			int win = checkWins.checkForWin(board,playStandard);
			if(win!=0){
				System.out.println("Player "+win+" has won");
				if(win == 1&&random.nextInt(5)==1){
					player1Color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
				}
				if(win == 2&&random.nextInt(5)==1){
					player2Color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
				}
			}
			//Repaint
			frame.repaint();
		}
	}
	
	/**
	 * Paints the board
	 * @param the graphics object on which to paint
	 */
	public void paintComponent(Graphics g){
		
		for(int x = 0;x<boardSize.x;x++){
			for(int y = 0; y<boardSize.y;y++){
				
				if(board[x][y] == 1){
					g.setColor(player1Color);
					
				}else if(board[x][y]== 2){
					g.setColor(player2Color);
				}else{
					g.setColor(emptyColor);
				}
				g.fillOval(x*tileSize, y*tileSize, tileSize, tileSize);
			}
		}
	}
	
	//	All functions need to be written (even if they are empty) in order to comply with the MouseListener interface
	
	/**
	 * Listens for mouse clicks and calls functions to place stones on the board 
	 */
	public void mouseClicked(MouseEvent mouse){
		placeStone(mouse);
    }
	/**
	 * Places a stone of the current color at the appropriate place on 
	 * the board
	 * @param the MouseEvent with information on where the player has clicked
	 */
	private void placeStone(MouseEvent mouse){
		if(board[mouse.getX()/tileSize][mouse.getY()/tileSize]==0){
			board[mouse.getX()/tileSize][mouse.getY()/tileSize] = currentColor;
			//Switch player
			if(currentColor == 1){
				currentColor = 2;
			}else{
				currentColor = 1;
			}
		}
	}
	
	public void mouseEntered(MouseEvent mouse){ 
	}   
    public void mouseExited(MouseEvent mouse){
    }
    public void mousePressed(MouseEvent mouse){
    }
    public void mouseReleased(MouseEvent mouse){ 
    }
    
    /**
     * The main function
     * @param does not respond to input arguments
     */
	public static void main(String args[]){
		//	Create a timer.
		java.util.Timer mainProgramTimer = new java.util.Timer();
		//	Create a new instance of the program
		GomokuOrig panel = new GomokuOrig();
		
		// 	Set up the settings of our JFrame
		//	Close program if window is closed
	    panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //	Set size of window
	    panel.frame.setSize(panel.screen.width, panel.screen.height);
	    //	Things should happen in the panel
	    panel.frame.setContentPane(panel); 
	    //	Make window visible
	    panel.frame.setVisible(true);
	    
		//	Do this regularly
	    mainProgramTimer.schedule(panel.gomokuTask, 0, 20);
	}
}
