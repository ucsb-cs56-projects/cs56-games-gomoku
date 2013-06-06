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

public class Gomoku extends JPanel implements MouseListener{
	
	//General variables, such as screen size etc.
	private Rectangle screen;	
	public JFrame frame;
	private Point boardSize;
	private int tileSize;
	private gomokuTimerTask gomokuTask;
	private Random random;
	
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
	
	public Gomoku(){
		
		super();
		
		//	General variables
		gomokuTask = new gomokuTimerTask();
		boardSize = new Point(70,40);
		tileSize = 20;
		int screenHeigth = boardSize.y*tileSize;
		int screenWidth = boardSize.x*tileSize;
		screen = new Rectangle(0, 0, screenWidth, screenHeigth);
		frame = new JFrame("Gomoku");
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
			int win = checkForWin();
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
	 * Checks if someone has won
	 * @return is 0 if nobody won, or the player number if someone won
	 */
	
	private int checkForWin(){
		int win = 0;
		win = checkHorizontalWin(board);
		if(win!=0){
			return win;
		}
		win = checkVerticalWin(board);
		if(win!=0){
			return win;
		}
		win = checkDownwardsDiagonals(board);
		if(win!=0){
			return win;
		}
		win = checkUpwardsDiagonals(board);
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
	private int checkUpwardsDiagonals(int[][] boardToCheck){
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
	private int checkDownwardsDiagonals(int[][] boardToCheck){
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
	private int checkLowerDownwardsDiagonals(int[][] boardToCheck){
		//First check diagonally from left to right downwards
		//Middle and lower diagonals
		for(int diagonalNo = 0;diagonalNo<boardToCheck[0].length;diagonalNo++){
			int y = diagonalNo;
			int x = 0;
			int maxInARow = 0;
			int lastColor = 0;
			while(x<boardToCheck.length&&y<boardToCheck[0].length){
				if(boardToCheck[x][y]==lastColor&&lastColor!=0){
					//Same as last color, and not empty
					maxInARow++;
				}else if(boardToCheck[x][y]!=lastColor&&boardToCheck[x][y]!=0){
					//Not same as last color, and not empty
					maxInARow = 1;
				}else{
					//Reset
					maxInARow = 0;
				}
				//Check for 5 in a row
				if(maxInARow >= 5){
					return lastColor;
				}
				//Update lastcolor
				lastColor = boardToCheck[x][y];
				
				x++;
				y++;
			}
			//Reset after each diagonal
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
	private int checkHorizontalWin(int[][] boardToCheck){
		//Horisontal
		int lastColor = 0;
		int maxInARow = 0;
		for(int y = 0;y<boardToCheck[0].length;y++){
			for(int x = 0;x<boardToCheck.length;x++){
				if(boardToCheck[x][y]==lastColor&&lastColor!=0){
					//Same as last color, and not empty
					maxInARow++;
				}else if(boardToCheck[x][y]!=lastColor&&boardToCheck[x][y]!=0){
					//Not same as last color, and not empty
					maxInARow = 1;
				}else{
					//Reset
					maxInARow = 0;
				}
				//Check for 5 in a row
				if(maxInARow >= 5){
					return lastColor;
				}
				//Update lastcolor
				lastColor = boardToCheck[x][y];
			}
			//Reset after each row
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
	private int checkVerticalWin(int[][] boardToTest){
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
		Gomoku panel = new Gomoku();
		
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
