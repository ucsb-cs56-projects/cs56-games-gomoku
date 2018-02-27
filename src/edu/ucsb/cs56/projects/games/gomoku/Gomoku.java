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
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
   * Gomoku class that display a board and allow two players to play
   * against each other
   * Edited by Eric Huang for project 02, Spring 2013
   
   @version CS56, Sprint 2013
*/
/**
 * edited for CS56 F16
 * 
 * @author Madhu Kannan, Colin Garrett
 */
/**
 * edited for CS56 W18
 * 
 * @author Yiyang Xu, Nikita Tyagi
 */

public class Gomoku extends JPanel implements MouseListener, MouseMotionListener {

	// General variables, such as screen size etc.
	private Rectangle screen;
	public JFrame frame;
	private Point boardSize;
	private int tileSize;
	public gomokuTimerTask gomokuTask;
	private Random random;
	private JButton toHomeMenu;
	private JButton resetButton;
	private JButton undoButton;

	// Game
	private int currentColor; // 1-4
	public int[][] grid;
	public Timer mainProgramTimer;
	public JFrame newFrame;
	private boolean isVisibleFrame = false;
	public boolean playStandard = true; // Standard Gomoku requires exactly 5.
	private int xc; // Freestyle allows 5 or more.This
	private int yc; // variable is set from the home
	private int turn; // screen's check box, in the Viewer
	// class.
	private int preX;
	private int preY;
	// Colors
	private Color player1Color = new Color(0, 200, 0); // 1
	private Color player1HoverColor = new Color(0, 255, 0); // 3
	private Color player2Color = new Color(0, 0, 200); // 2
	private Color player2HoverColor = new Color(0, 0, 255); // 4
	private Color emptyColor = new Color(200, 200, 200);

	/**
	 * constructor that creates a board, set the grid empty, set the initial
	 * color
	 */
	public Gomoku() {

		super();
		this.setLayout(null);

		// General variables
		gomokuTask = new gomokuTimerTask();
		mainProgramTimer = new Timer(30, gomokuTask);
		boardSize = new Point(15, 15);
		grid = new int[boardSize.x][boardSize.y];
		for (int x = 0; x < boardSize.x; x++) {
			for (int y = 0; y < boardSize.y; y++) {
				grid[x][y] = 0;
			}
		}
		tileSize = 20;
		int screenHeight = boardSize.y * tileSize + tileSize;
		int screenWidth = boardSize.x * tileSize + 500;
		screen = new Rectangle(0, 0, screenWidth, screenHeight);
		frame = new JFrame("Gomoku");
		random = new Random();

		// Game
		currentColor = 1;

		// Add listeners that keep track of the mouse
		addMouseListener(this);
		addMouseMotionListener(this);
		newFrame = new JFrame();
		newFrame.setVisible(isVisibleFrame);

		// Start the mainProgramTimer
		mainProgramTimer.start();

		// The following button returns to the home menu
		toHomeMenu = new JButton("Return to Home Menu");
		toHomeMenu.addActionListener((x) -> {
			Viewer.showHomePanel();
			resetBoard();
			setCurrentColor(1);
			isVisibleFrame = false;
			newFrame.setVisible(isVisibleFrame);
		});
		this.add(toHomeMenu);
		toHomeMenu.setBounds(boardSize.x * tileSize + 50, boardSize.y / 5, 200, 50);

		// The following button resets the board
		resetButton = new JButton("New Game");
		resetButton.addActionListener((x) -> {
			resetBoard();
			setCurrentColor(1);
			repaint();
			isVisibleFrame = false;
			newFrame.setVisible(isVisibleFrame);
		});
		this.add(resetButton);
		resetButton.setBounds(boardSize.x * tileSize + 50, boardSize.y / 5 + 60, 200, 50);

		JLabel title = new JLabel(new ImageIcon("src/edu/ucsb/cs56/projects/games/gomoku/gomoku.png"));
		this.add(title);
		title.setBounds(20, boardSize.y * tileSize + 20, 550, 150);

		undoButton = new JButton("Undo Move");
		undoButton.addActionListener((x) -> {
			turn = grid[xc][yc];
			setGrid(xc, yc, 0);
			repaint();
			if (turn == 2) {
				setCurrentColor(2);
			} else {
				setCurrentColor(1);
			}
		});
		this.add(undoButton);
		undoButton.setBounds(boardSize.x * tileSize + 50, boardSize.y / 5 + 120, 200, 50);

	}

	/**
	 * repaint the board
	 */
	class gomokuTimerTask implements ActionListener {
		// boolean playAgainFrame = false;
		// Main loop, done every iteration.
		public void run() {
			int win = CheckWins.checkForWin(grid, playStandard);
			if (win != 0) {
				JLabel theWinner = new JLabel("something is wrong with the code");
				System.out.println("Player " + win + " has won");
				if (win == 1) {
					theWinner = new JLabel("Congratulations, player one, you won!");

				} else {
					theWinner = new JLabel("Congratulations, player two, you won!");

				}
				JLabel text = new JLabel("Do you want to play again?");
				JButton playAgainButton = new JButton("Play Again");
				playAgainButton.addActionListener((x) -> {
					resetBoard();
					repaint();
					isVisibleFrame = false;
					newFrame.setVisible(isVisibleFrame);
				});
				JButton returnToTitleScreenButton = new JButton("Main Menu");
				returnToTitleScreenButton.addActionListener((x) -> {
					Viewer.showHomePanel();
					resetBoard();
					isVisibleFrame = false;
					newFrame.setVisible(isVisibleFrame);
				});
				JPanel content = new JPanel();
				content.add(theWinner);
				content.add(text);
				content.add(playAgainButton);
				content.add(returnToTitleScreenButton);
				newFrame.getContentPane().add(content);
				newFrame.setSize(300, 300);
				isVisibleFrame = true;
				newFrame.setVisible(isVisibleFrame);
			}
			// Repaint
			frame.repaint();
		}

		public void actionPerformed(ActionEvent e) {
			run();
		}

	}

	/**
	 * the method to set the color of each grid and then switches player
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 5000, 5000);
		for (int x = 0; x < boardSize.x; x++) {
			for (int y = 0; y < boardSize.y; y++) {
				if (grid[x][y] == 1) {
					g2.setColor(player1Color);
				} else if (grid[x][y] == 2) {
					g2.setColor(player2Color);
				} else if (grid[x][y] == 3) {
					g2.setColor(player1HoverColor);
				} else if (grid[x][y] == 4) {
					g2.setColor(player2HoverColor);
				} else {
					g2.setColor(emptyColor);
				}
				g2.fillOval(x * tileSize, y * tileSize, tileSize, tileSize);
			}
		}
		if (getCurrentColor() == 1) {
			Stroke thick = new BasicStroke(4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			g2.setStroke(thick);
			g2.clearRect(boardSize.x * tileSize + 10, boardSize.y * tileSize / 2 - 10, 500, tileSize);
			g2.setColor(player1Color);
			g2.drawString("Player one, it is your turn. You are green.", boardSize.x * tileSize + 10,
					boardSize.y * tileSize / 2 + 50);
		} else if (getCurrentColor() == 2) {
			g2.clearRect(boardSize.x * tileSize + 10, boardSize.y * tileSize / 2 - 10, 500, tileSize);
			g2.setColor(player2Color);
			g2.drawString("Player two, it is your turn. You are blue.", boardSize.x * tileSize + 10,
					boardSize.y * tileSize / 2 + 50);
		}
	}

	// All functions need to be written (even if they are empty)
	// in order to comply with the MouseListener interface
	public void mouseClicked(MouseEvent mouse) {
		Controller c = new Controller(this);
		c.coordinate(mouse);
		// if statement to check if within bounds
		if (c.getXCoord() < boardSize.x && c.getYCoord() < boardSize.y) {
			// checks if there is already a piece on the spot
			if (grid[c.getXCoord()][c.getYCoord()] != 1 && grid[c.getXCoord()][c.getYCoord()] != 2) {
				xc = c.getXCoord();
				yc = c.getYCoord();
				// if no piece then colors that piece
				setGrid(c.getXCoord(), c.getYCoord(), getCurrentColor());
				// Switch player
				if (getCurrentColor() == 1) {
					setCurrentColor(2);
				} else {
					setCurrentColor(1);
				}
				repaint();
			} else {
				// throws error message if they try to put their piece on
				// another
				// player's
				JOptionPane.showMessageDialog(null, "You can't put your pieces over other players!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/** empty for now */
	public void mouseEntered(MouseEvent mouse) {

		/*
		 * Controller c = new Controller(this); c.coordinate(mouse); if
		 * (c.getXCoord() < boardSize.x && c.getYCoord() < boardSize.y) { if
		 * (getCurrentColor() == 1 || getCurrentColor() == 3) {
		 * setCurrentColor(4); setGrid(c.getXCoord(), c.getYCoord(),
		 * getCurrentColor()); } else if (getCurrentColor() == 2 ||
		 * getCurrentColor() == 4) { setCurrentColor(3); setGrid(c.getXCoord(),
		 * c.getYCoord(), getCurrentColor()); } else { setCurrentColor(0);
		 * setGrid(c.getXCoord(), c.getYCoord(), getCurrentColor()); }
		 * repaint(); }
		 */

	}

	/** empty for now */
	public void mouseExited(MouseEvent mouse) {
	}

	/** empty for now */
	public void mousePressed(MouseEvent mouse) {
	}

	/** empty for now */
	public void mouseReleased(MouseEvent mouse) {
	}

	/** empty for now */
	public void mouseDragged(MouseEvent mouse) {
	}

	/** empty for now */
	public void mouseMoved(MouseEvent mouse) {
	       
	    /* Controller c = new Controller(this);
		  c.coordinate(mouse);
		  if (c.getXCoord() < boardSize.x && c.getYCoord() < boardSize.y){
		    if(grid[c.getXCoord()][c.getYCoord()]!=1 && grid[c.getXCoord()][c.getYCoord()]!=2){
		      if(grid[preX][preY] == 3 || grid[preX][preY] == 4){   
		           setGrid(preX,preY,0);
		           repaint();
		      }
		       if ((getCurrentColor() == 1) && (grid[c.getXCoord()][c.getYCoord()]!=1)){
			 setGrid(c.getXCoord(),c.getYCoord(),3);
		       }else if (getCurrentColor() == 2 && (grid[c.getXCoord()][c.getYCoord()]!=2)){
			 setGrid(c.getXCoord(),c.getYCoord(),4);
		       }
		     repaint();
		     preX = c.getXCoord();
		     preY = c.getYCoord();
		    }
		    }*/
	}

	/**
	 * resets the board state
	 */
	public void resetBoard() {
		for (int a = 0; a < grid.length; a++) {
			for (int b = 0; b < grid[a].length; b++) {
				grid[a][b] = 0;
			}
		}
	}

	/**
	 * getter for the currentColor
	 * 
	 * @return the number that represent the color
	 */
	public int getCurrentColor() {
		return currentColor;
	}

	/**
	 * setter for currentCollar
	 * 
	 * @param currentColor
	 *            the new color to replace what is in the grid
	 */
	public void setCurrentColor(int currentColor) {
		this.currentColor = currentColor;
	}

	/**
	 * setter for the color in the grid
	 * 
	 * @param xCoord
	 *            the x coordinate
	 * @param yCoord
	 *            the y coordinate
	 * @param newColor
	 *            the new color desired
	 */
	public void setGrid(int xCoord, int yCoord, int newColor) {
		// System.out.println (xCoord + " " + yCoord);
		grid[xCoord][yCoord] = newColor;

	}

	/**
	 * getter for tileSize
	 * 
	 * @return the size of the tile
	 */
	public int getTileSize() {
		return this.tileSize;
	}

	/**
	 * getter for the width of the screen get the width of the screen and cast
	 * it to integer
	 * 
	 * @return int
	 */
	public int getScreenWidth() {
		return (int) (screen.getWidth());
	}

	/**
	 * getter for the width of the screen get the width of the screen and cast
	 * it to integer
	 * 
	 * @return int
	 */
	public int getScreenHeight() {
		return (int) (this.screen.getHeight());
	}
}
