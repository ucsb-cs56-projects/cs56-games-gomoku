package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

class InstructionsPanel extends JFrame {

	public InstructionsPanel() {

		// Instruction text is split into three lines.
		JPanel panel = new JPanel();
		

		// Now we set a title for this screen
		JLabel title = new JLabel("Instructions");
		Font titleFont = new Font("sans_serif", Font.BOLD, 28);
		title.setFont(titleFont);
		this.getContentPane().add(BorderLayout.NORTH, title);

		// Button to return back to the home screen.
		JButton toHomePanel = new JButton("Back to Home Screen");
		toHomePanel.addActionListener((x) -> {
			Viewer.showHomePanel();
		});
		this.getContentPane().add(BorderLayout.SOUTH, toHomePanel);

		JLabel instruct = new JLabel("Gomoku is a two-player strategy game.");
		JPanel instructPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructPanel.add(instruct);
		JLabel instructTwo = new JLabel("Players alternate clicking an empty circle, to indicate placing a 'stone' of their color.");
		JPanel instructTwoPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructTwoPanel.add(instructTwo);
		JLabel instructThree = new JLabel("The winner is the first player to get an unbroken row of five stones horizontally, vertically, or diagonally.");
		JPanel instructThreePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructThreePanel.add(instructThree);
		JLabel instructStandard = new JLabel("Standard Gomoku requires exactly five in a row to win.");
		JPanel instructStandardPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructStandardPanel.add(instructStandard);
		JLabel instructFreestyle = new JLabel("Freestyle Gomoku allows five or more stones in a row to win.");
		JPanel instructFreestylePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructFreestylePanel.add(instructFreestyle);
		JLabel instructCheck = new JLabel("Default game is Standard. Change to Freestlye by checking the appropriate Settings box on the Home Screen.");
		JPanel instructCheckPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructCheckPanel.add(instructCheck);
		JLabel instructHouse = new JLabel("The rule of three and three bans a move that simultaneously forms two open rows of three stones (rows not blocked by an opponent's stone at either end). "
						  /*The rule of four and four bans a move that simultaneously forms two rows of four stones (open or not)."Alternatively, a handicap may be given such that after the first three and three play has been made, the opposing player may place two stones as their next turn. These stones must block an opponent's row of three. Efforts to improve fairness by reducing first-move advantage include the rule of swap, generalizable as swap-(x,y,z) and characterizable as a partially compounded and partially iterated version of the pie rule (one person slices; the other chooses): One player places on the board x stones of the first-moving color and a lesser number y stones of the second-moving color (slicing in the pie metaphor); the other player is entitled to choose between a) playing from the starting position, in which case the selecting player is also entitled to choose which color to play, and b) placing z (usually [(x - y) + 1]) more stones on the board at locations of that player's choice (reslicing in the pie metaphor, with limitations created by the board's existing setup akin to limitations arising from the existing slices in the pie), in which case the former player is entitled to choose which color side to play."*/
						  );
		JPanel instructHousePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		instructHousePanel.add(instructHouse);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(instructPanel);
		panel.add(instructTwoPanel);
		panel.add(instructThreePanel);
		panel.add(instructStandardPanel);
		panel.add(instructFreestylePanel);
		panel.add(instructCheckPanel);
		panel.add(instructHousePanel);
		
		this.getContentPane().add(BorderLayout.CENTER, panel);

	}
}
