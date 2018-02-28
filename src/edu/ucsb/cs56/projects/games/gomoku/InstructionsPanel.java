package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

class InstructionsPanel extends JPanel {

	public InstructionsPanel() {

		// Instruction text is split into three lines.
		JPanel panel = new JPanel();

		// Now we set a title for this screen
		JLabel title = new JLabel("Instructions");
		Font titleFont = new Font("sans_serif", Font.BOLD, 28);
		title.setFont(titleFont);
		this.add(BorderLayout.NORTH, title);

		// Button to return back to the home screen.
		JButton toHomePanel = new JButton("Back to Home Screen");
		toHomePanel.addActionListener((x) -> {
			Viewer.showHomePanel();
		});
		this.add(BorderLayout.SOUTH, toHomePanel);
		JLabel empty = new JLabel("                            ");
		empty.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JLabel instruct = new JLabel("Gomoku is a two-player strategy game.");
		// instruct.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JLabel instructTwo = new JLabel(
				"Players alternate clicking an empty circle, to indicate placing a 'stone' of their color.");
		// instructTwo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JLabel instructThree = new JLabel(
				"The winner is the first player to get an unbroken row of five stones horizontally,");
		JLabel instructFour = new JLabel("vertically, or diagonally.");
		JLabel instructStandard = new JLabel("Standard Gomoku requires exactly five in a row to win.");
		JLabel instructFreestyle = new JLabel("Freestyle Gomoku allows five or more stones in a row to win.");
		JLabel instructCheckOne = new JLabel(
				"Default game is Standard. Change to Freestlye by checking the appropriate");
		JLabel instructCheckTwo = new JLabel("Settings box on the Home Screen.");
		JLabel instructHouseRule = new JLabel("The instructions for House Rules game are as follows:");
		JLabel instructHouseRuleOne = new JLabel(
				"<html>The rule of three and three bans a move that simultaneously forms<br/>two open rows of three stones (rows not blocked by an opponent's stone at either end).<br/>The rule of four and four bans a move that simultaneously forms two rows of four<br/>stones (open or not). Alternatively, a handicap may be given such that after the first<br/>three and three play has been made, the opposing player may place two stones as<br/>their next turn.These stones must block an opponent's row of three.</html>");
		JLabel instructHouseRuleTwo = new JLabel(
				"<html>Efforts to improve fairness by reducing first-move advantage include the<br/>rule of swap, generalizable as swap-(x,y,z) and characterizable<br/>as a partially compounded and partially iterated version of the pie rule<br/>(one person slices; the other chooses): One player places on the board x stones<br/>of the first-moving color and a lesser number y stones of the second-moving color<br/>(slicing in the pie metaphor); the other player is entitled to choose between<br/>a) playing from the starting position, in which case the selecting player is also entitled<br/>to choose which color to play<br/>b) placing z (usually [(x - y) + 1]) more stones on the board at locations of that<br/>player's choice(reslicing in the pie metaphor, with limitations created by the<br/>board's existing setup akin to limitations arising from the existing slices in the pie),<br/>in which case the former player is entitled to choose which color side to play.</html>");

		panel.add(empty);
		panel.add(instruct);
		panel.add(instructTwo);
		panel.add(instructThree);
		panel.add(instructFour);
		panel.add(instructStandard);
		panel.add(instructFreestyle);
		panel.add(instructCheckOne);
		panel.add(instructCheckTwo);
		panel.add(instructHouseRule);
		panel.add(instructHouseRuleOne);
		panel.add(instructHouseRuleTwo);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(BorderLayout.CENTER, panel);

	}
}
