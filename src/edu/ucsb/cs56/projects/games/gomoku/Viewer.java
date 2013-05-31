package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.JFrame;
import java.util.TimerTask;
import java.util.Timer;

public class Viewer
{
	public static void main(String args[]){
		
		//	Create a timer.
		Timer mainProgramTimer = new Timer();
		//	Create a new instance of the program
		Gomoku panel = new Gomoku();
		
		// 	Set up the settings of our JFrame

	    //	Set size of window
	    panel.frame.setSize(panel.getScreenWidth(), panel.getScreenHeight());
	    
	    //  Set the title
	    panel.frame.setTitle("Gomoku");
	    
	    //	Things should happen in the panel
	    panel.frame.setContentPane(panel); 

		panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //	Make window visible
	    panel.frame.setVisible(true);
	    
		//	Do this regularly
	    mainProgramTimer.schedule(panel.gomokuTask, 0, 20);
	}
}
