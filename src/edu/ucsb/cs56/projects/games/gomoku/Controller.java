package edu.ucsb.cs56.projects.games.gomoku;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * a controller to control user interactions, such as clicking button Last
 * edited by Eric Huang for project 2 Spring 2013
 * 
 * @see Gomoku
 */

public class Controller {
	/*
	 * ignore my comments from earlier, that was just me trying to ignore the
	 * problem
	 */

	// declare the variables
	Gomoku panel = new Gomoku();
    GomokuSinglePlayer singlePanel = new GomokuSinglePlayer();
	int xCoord, yCoord;

	/**
	 * constructor intialize the default to 0, and set the panel because later I
	 * want to use getTileSize
	 * 
	 * @param panel
	 *            the panel of the Gomoku game
	 */
	public Controller(Gomoku panel) {
		// set the default to zero
		xCoord = 0;
		yCoord = 0;
		this.panel = panel; // for later uses
	}
public Controller(GomokuSinglePlayer singlePanel) {
		// set the default to zero
		xCoord = 0;
		yCoord = 0;
		this.singlePanel = singlePanel; // for later uses
	}

	/**
	 * get the correct "coordinate" on the grid, so to speak
	 * 
	 * @param mouse
	 *            where the user clicked on the grid
	 */
	public void coordinate(MouseEvent mouse) {
		// get the correct coordinate on the grid
		xCoord = mouse.getX() / panel.getTileSize();
		yCoord = mouse.getY() / panel.getTileSize();
	}

	/**
	 * getter for the x coordinate
	 * 
	 * @return x coordinate
	 */
	public int getXCoord() {
		return this.xCoord;
	}

	/**
	 * getter for the y coordinate
	 * 
	 * @return y coordinate
	 */
	public int getYCoord() {
		return this.yCoord;
	}

}
