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
	public static boolean setPlayFreestyle = false;


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
		
                JCheckBox check = new JCheckBox("Check to change to Freestyle Gomoku.");
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

		JLabel SettingsTitle = new JLabel("\n"+ "Settings:" + "\n");
	

		JPanel OptionsPanel = new JPanel();
		JPanel SettingsPanel = new JPanel();
		
		OptionsPanel.add(getGameButton);
		OptionsPanel.add(getInstructionsButton);
		
		SettingsPanel.add(SettingsTitle);
		SettingsPanel.add(check);
		
		OptionsPanel.setLayout(new BoxLayout(OptionsPanel, BoxLayout.Y_AXIS));
		SettingsPanel.setLayout(new BoxLayout(SettingsPanel, BoxLayout.Y_AXIS));
		
		frame.getContentPane().add(BorderLayout.EAST, OptionsPanel);
		frame.getContentPane().add(BorderLayout.NORTH, title);
		frame.getContentPane().add(BorderLayout.CENTER, SettingsPanel);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}

//inner class for actual game screen. All code in actionPerformed taken from Eric Huang's implementation.
/*
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
*/

//inner class for Instructions
class InstructionsListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event){
		JFrame frame = new JFrame();


		//Instruction text is split into three lines. 
		JPanel panel = new JPanel();
		JLabel instruct = new JLabel("Gomoku is a two-player strategy game.");
		JLabel instructTwo = new JLabel(" Players alternate clicking an empty circle, to indicate placing a 'stone' of their color.");
		JLabel instructThree = new JLabel(" The winner is the first player to get an unbroken row of five stones horizontally, vertically, or diagonally.");
		JLabel instructStandard = new JLabel("Standard Gomoku requires exactly five in a row to win.");
		JLabel instructFreestyle = new JLabel("Freestyle Gomoku allows five or more stones in a row to win.");
		JLabel instructCheck = new JLabel("Defaul game is Standard. Change to Freestlye by checking the appropriate Settings box on the Home Screen.");
		panel.add(instruct);
		panel.add(instructTwo);
		panel.add(instructThree);
		panel.add(instructStandard);
		panel.add(instructFreestyle);
		panel.add(instructCheck);
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

}
