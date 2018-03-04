package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

/**
 * viewer for the Gomoku game
 * 
 * edited by Eric Huang for project 2 Spring 2013, CS56
 */

/**
 * @author Madhu Kannan CS56 F16
 * @author Colin Garrett CS56 F16 Edited to add home screen and end screen.
 */

public class Viewer extends JPanel {
	/**
	 * main class for the gomoku game creates instance of the program, set up
	 * the board
	 */

	// Edited F16: main now only contains home screen. Inner classes will
	// contain game and end screens.

	private JFrame frame;
	private static JPanel container;
	public static CardLayout cards;
	public static HomePanel hPanel;
	public static InstructionsPanel iPanel;
	public static Gomoku gsPanel;
	public static GomokuSinglePlayer SinglePlayerPanel;

	public static boolean setPlayFreestyle = false;
	public static boolean multiPlayer = false;
	public final static String HOMEPANEL = "Card with home screen";
	public final static String INSTRUCTIONPANEL = "Card with instructions";
	public final static String GAMEPANEL = "Card with the game";
	public final static String SINGLEGAMEPANEL = "Card with single player game";

	public static void main(String args[]) {
		Viewer gui = new Viewer();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cards = new CardLayout();
		container = new JPanel(cards);

		hPanel = new HomePanel();
		container.add(hPanel, HOMEPANEL);

		iPanel = new InstructionsPanel();
		container.add(iPanel, INSTRUCTIONPANEL);

		gsPanel = new Gomoku();
		container.add(gsPanel, GAMEPANEL);

		SinglePlayerPanel = new GomokuSinglePlayer();
		container.add(SinglePlayerPanel, SINGLEGAMEPANEL);

		frame.add(container);

		frame.setSize(700, 600);
		frame.setVisible(true);
	}

	public static void showHomePanel() {
		((CardLayout) (container.getLayout())).show(container, HOMEPANEL);
	}

	public static void showInstructionsPanel() {
		((CardLayout) (container.getLayout())).show(container, INSTRUCTIONPANEL);
	}

	public static void showGamePanel() {
		((CardLayout) (container.getLayout())).show(container, GAMEPANEL);
	}

	public static void showSingleGamePanel() {
		((CardLayout) (container.getLayout())).show(container, SINGLEGAMEPANEL);
	}

}
