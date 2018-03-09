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
import java.lang.Math;
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

public class GomokuSinglePlayer extends JPanel implements MouseListener, MouseMotionListener {

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
	private Color player1HoverColor = new Color(204, 255, 102); // 3
	private Color player2Color = new Color(0, 0, 200); // 2
	// private Color player2HoverColor = new Color(102, 204, 255); // 4
	private Color emptyColor = new Color(200, 200, 200);

	/**
	 * constructor that creates a board, set the grid empty, set the initial
	 * color
	 */
	public GomokuSinglePlayer() {

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
				if (win == 1) {
					theWinner = new JLabel("Congratulations, player one, you won!");
				} else {
					theWinner = new JLabel("Sorry, player one, you lost!");
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
	 * the method to set the color of each grid
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
				} else {
					g2.setColor(emptyColor);
				}
				g2.fillOval(x * tileSize, y * tileSize, tileSize, tileSize);
			}
		}
	}

	// All functions need to be written (even if they are empty)
	// in order to comply with the MouseListener interface
	public void mouseClicked(MouseEvent mouse) {
		int win = CheckWins.checkForWin(grid, playStandard);
		if (win == 0) {
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
					setCurrentColor(2);
					playMove();
					setCurrentColor(1);
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
	}

	/** empty for now */
	public void mouseEntered(MouseEvent mouse) {
	}

	/** empty for now */
	public void mouseExited(MouseEvent mouse) {
		int win = CheckWins.checkForWin(grid, playStandard);
		if (win == 0) {
			if (grid[preX][preY] != 1 && grid[preX][preY] != 2) {
				setGrid(preX, preY, 0);
				repaint();
			}
		}
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
		int win = CheckWins.checkForWin(grid, playStandard);
		if (win == 0) {
			Controller c = new Controller(this);
			c.coordinate(mouse);
			if (c.getXCoord() < boardSize.x && c.getYCoord() < boardSize.y) {
				if (grid[c.getXCoord()][c.getYCoord()] != 1 && grid[c.getXCoord()][c.getYCoord()] != 2) {
					if (grid[preX][preY] == 3 || grid[preX][preY] == 4) {
						setGrid(preX, preY, 0);
						repaint();
					}
					if ((getCurrentColor() == 1) && (grid[c.getXCoord()][c.getYCoord()] != 1)) {
						setGrid(c.getXCoord(), c.getYCoord(), 3);
					} else if (getCurrentColor() == 2 && (grid[c.getXCoord()][c.getYCoord()] != 2)) {
						setGrid(c.getXCoord(), c.getYCoord(), 4);
					}
					repaint();
					preX = c.getXCoord();
					preY = c.getYCoord();
				}
			} else {
				if (grid[preX][preY] != 1 && grid[preX][preY] != 2) {
					setGrid(preX, preY, 0);
					repaint();
				}
			}
		}
	}

	public void computerRandomMove() {
		int win = CheckWins.checkForWin(grid, playStandard);
		if (win != 1) {
			int x = (int) (Math.random() * boardSize.x);
			int y = (int) (Math.random() * boardSize.y);
			boolean empty = true;
			while (empty) {
				if (grid[x][y] != 1 && grid[x][y] != 2) {
					setGrid(x, y, 2);
					empty = false;
				} else {
					x = (int) (Math.random() * boardSize.x);
					y = (int) (Math.random() * boardSize.y);
				}
			}
		}
	}

	public void playMove() {
		int currentBest = -1000;
		int bestX = -1;
		int bestY = -1;
		int[][] gridCopy = new int[boardSize.x][boardSize.y];
		int score = -1000;
		for (int i = 0; i < boardSize.x; i++) {
			for (int j = 0; j < boardSize.y; j++) {
				if (grid[i][j] != 1 && grid[i][j] != 2) {
					for (int a = 0; a < boardSize.x; a++) {
						for (int b = 0; b < boardSize.y; b++) {
							gridCopy[a][b] = grid[a][b];
						}
					}
					gridCopy[i][j] = 2;
					score = evaluateBoard(gridCopy);
					if (score > currentBest) {
						currentBest = score;
						bestX = i;
						bestY = j;
					}
				}
			}
		}
		setGrid(bestX, bestY, 2);
	}

    public int nearbyPlayersPoints(int[][] board){
	int score = 0;
		for (int i = 0; i < boardSize.x; i++) {
			for (int j = 0; j < boardSize.y; j++) {
				if (board[i][j] == 2) {
					// no corner or edge piece
					if (i != 0 && i != boardSize.x - 1 && j != 0 && j != boardSize.y - 1) {
						if (board[i][j - 1] == 1) {
							score -= 1;
						} else if (board[i][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j] == 1) {
							score -= 1;
						} else if (board[i + 1][j] == 2) {
							score += 1;
						}
						if (board[i + 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i][j + 1] == 1) {
							score -= 1;
						} else if (board[i][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j] == 1) {
							score -= 1;
						} else if (board[i - 1][j] == 2) {
							score += 1;
						}
						if (board[i - 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j - 1] == 2) {
							score += 1;
						}
					}
					// upper left corner
					else if (i == 0 && j == 0) {
						if (board[i + 1][j] == 1) {
							score -= 1;
						} else if (board[i + 1][j] == 2) {
							score += 1;
						}
						if (board[i + 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i][j + 1] == 1) {
							score -= 1;
						} else if (board[i][j + 1] == 2) {
							score += 1;
						}
					}
					// upper right corner
					else if (i == boardSize.x - 1 && j == 0) {
						if (board[i][j + 1] == 1) {
							score -= 1;
						} else if (board[i][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j] == 1) {
							score -= 1;
						} else if (board[i - 1][j] == 2) {
							score += 1;
						}
					}
					// lower left corner
					else if (i == 0 && j == boardSize.y - 1) {
						if (board[i][j - 1] == 1) {
							score -= 1;
						} else if (board[i][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j] == 1) {
							score -= 1;
						} else if (board[i + 1][j] == 2) {
							score += 1;
						}
					}
					// lower right corner
					else if (i == boardSize.x - 1 && j == boardSize.y - 1) {
						if (board[i][j - 1] == 1) {
							score -= 1;
						} else if (board[i][j - 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j] == 1) {
							score -= 1;
						} else if (board[i - 1][j] == 2) {
							score += 1;
						}
						if (board[i - 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j - 1] == 2) {
							score += 1;
						}
					}
					// top edge minus corners
					else if (j == 0 && i != 0 && i != boardSize.x - 1) {
						if (board[i + 1][j] == 1) {
							score -= 1;
						} else if (board[i + 1][j] == 2) {
							score += 1;
						}
						if (board[i + 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i][j + 1] == 1) {
							score -= 1;
						} else if (board[i][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j] == 1) {
							score -= 1;
						} else if (board[i - 1][j] == 2) {
							score += 1;
						}
					}
					// right edge minus corners
					else if (i == boardSize.x - 1 && j != 0 && j != boardSize.y - 1) {
						if (board[i][j - 1] == 1) {
							score -= 1;
						} else if (board[i][j - 1] == 2) {
							score += 1;
						}
						if (board[i][j + 1] == 1) {
							score -= 1;
						} else if (board[i][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i - 1][j] == 1) {
							score -= 1;
						} else if (board[i - 1][j] == 2) {
							score += 1;
						}
						if (board[i - 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j - 1] == 2) {
							score += 1;
						}
					}
					// bottom edge minus corners
					else if (i != 0 && i != boardSize.x - 1 && j == boardSize.y - 1) {
						if (board[i][j - 1] == 1) {
							score -= 1;
						} else if (board[i][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j] == 1) {
							score -= 1;
						} else if (board[i + 1][j] == 2) {
							score += 1;
						}
						if (board[i - 1][j] == 1) {
							score -= 1;
						} else if (board[i - 1][j] == 2) {
							score += 1;
						}
						if (board[i - 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i - 1][j - 1] == 2) {
							score += 1;
						}
					}
					// left edge minus corners
					else if (i == 0 && j != 0 && j != boardSize.y - 1) {
						if (board[i][j - 1] == 1) {
							score -= 1;
						} else if (board[i][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j - 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j - 1] == 2) {
							score += 1;
						}
						if (board[i + 1][j] == 1) {
							score -= 1;
						} else if (board[i + 1][j] == 2) {
							score += 1;
						}
						if (board[i + 1][j + 1] == 1) {
							score -= 1;
						} else if (board[i + 1][j + 1] == 2) {
							score += 1;
						}
						if (board[i][j + 1] == 1) {
							score -= 1;
						} else if (board[i][j + 1] == 2) {
							score += 1;
						}
					}
				}
			}
		}
		return score;
    }
    
	public int evaluateBoard(int[][] board) {
	    int nearbyPoints = nearbyPlayersPoints(board);
	    int result = nearbyPoints;
	    return result;
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
