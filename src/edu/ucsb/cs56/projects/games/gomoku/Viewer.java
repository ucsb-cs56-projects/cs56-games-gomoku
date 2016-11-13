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
 * @author Colin Garrett CS56 F16
 * Edited to add home screen and end screen. 
 */
	

public class Viewer
{
	/**
	 * main class for the gomoku game
	 * creates instance of the program, set up the board
	 */

	//Edited F16: main now only contains home screen. Inner classes will contain game and end screens.
	
	JFrame frame;
	public boolean setPlayFreestyle = false;


	public static void main(String args[]){
		Viewer gui = new Viewer();
		gui.go();
	}


	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//Set title for this screen.
		JLabel title = new JLabel("Gomoku");
		Font titleFont = new Font("sans_serif", Font.BOLD, 28);
		title.setFont(titleFont);
		
		//When clicked, this button takes user to game screen.
		JButton getGameButton = new JButton("Play!");
		getGameButton.addActionListener(new GameScreenListener());
		//When clicked, this button takes user to instructions. 
		JButton getInstructionsButton = new JButton("Instructions");
		getInstructionsButton.addActionListener(new InstructionsListener());
		//When clicked, this button takes user to settings.
		JButton getSettingsButton = new JButton("Settings");
		getSettingsButton.addActionListener(new SettingsScreenListener());
			
		JPanel panel = new JPanel();
		panel.add(getGameButton);
		panel.add(getInstructionsButton);
		panel.add(getSettingsButton);
		frame.getContentPane().add(BorderLayout.WEST, panel);
		frame.getContentPane().add(BorderLayout.NORTH, title);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

//inner class for actual game screen. All code in actionPerformed taken from Eric Huang's implementation.

class GameScreenListener implements ActionListener {

	public void actionPerformed(ActionEvent event){

		//	Create a timer.
		//Timer mainProgramTimer = new Timer();
		//	Create a new instance of the program
		Gomoku panel = new Gomoku();
		
		// 	Set up the settings of our JFrame

		panel.mainProgramTimer = new Timer();

		panel.playStandard = !setPlayFreestyle;

	    //	Set size of window
	    panel.frame.setSize(panel.getScreenWidth(), panel.getScreenHeight());
	    
	    //  Set the title
	    panel.frame.setTitle("Gomoku");
	    
	    //	Things should happen in the panel
	    panel.frame.setContentPane(panel); 

		//panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this is the only part of Eric Huang's original code commented out; we want exit to be tied to home screen, not game screen. 
	    
	    //	Make window visible
	    panel.frame.setVisible(true);
	    
		//	Do this regularly
	    panel.mainProgramTimer.schedule(panel.gomokuTask, 0, 20);
	}
}


//inner class for Instructions
class InstructionsListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event){
		JFrame frame = new JFrame();


		//Instruction text is split into three lines. 
		JPanel panel = new JPanel();
		JLabel instruct = new JLabel("Gomoku is a two-player strategy game.");
		JLabel instructTwo = new JLabel(" Players alternate clicking an empty circle, to indicate placing a 'stone' of their color.");
		JLabel instructThree = new JLabel(" The winner is the first player to get an unbroken row of five stones horizontally, vertically, or diagonally.");
		panel.add(instruct);
		panel.add(instructTwo);
		panel.add(instructThree);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(BorderLayout.WEST, panel);
		
		//Now we set a title for this screen
		JLabel title = new JLabel("Instructions");
		Font titleFont = new Font("sans_serif", Font.BOLD, 28);
		title.setFont(titleFont);
		frame.getContentPane().add(BorderLayout.NORTH, title);


		frame.setSize(900, 900);
		frame.setVisible(true);
	}
}

class SettingsScreenListener implements ActionListener{

	public void actionPerformed(ActionEvent event){

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JCheckBox check = new JCheckBox("Check to change to Freestyle Gomoku. (Default is Standard.)");
		check.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e){
				if(check.isSelected()){
					setPlayFreestyle = true;
				}
				if(!check.isSelected()){
					setPlayFreestyle = false;
				}
			}
		}
		);

		panel.add(check);
		frame.getContentPane().add(panel);
		frame.setSize(300,300);
		frame.setVisible(true);
	}


}

}
