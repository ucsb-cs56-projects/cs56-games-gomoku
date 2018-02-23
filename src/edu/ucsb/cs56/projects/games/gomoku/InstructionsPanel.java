package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

class InstructionsPanel extends JPanel {
    
    public InstructionsPanel(){
        
        
        //Instruction text is split into three lines.
        JPanel panel = new JPanel();
        
        //Now we set a title for this screen
        JLabel title = new JLabel("Instructions");
        Font titleFont = new Font("sans_serif", Font.BOLD, 28);
        title.setFont(titleFont);
        this.add(BorderLayout.NORTH, title);
        
        // Button to return back to the home screen.
        JButton toHomePanel = new JButton ("Back to Home Screen");
        toHomePanel.addActionListener ((x)-> { Viewer.showHomePanel(); } );
        this.add (BorderLayout.SOUTH, toHomePanel);
        
        JLabel instruct = new JLabel("Gomoku is a two-player strategy game.");
	//instruct.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel instructTwo = new JLabel("Players alternate clicking an empty circle, to indicate placing a 'stone' of their color.");
        JLabel instructThree = new JLabel("The winner is the first player to get an unbroken row of five stones horizontally, vertically, or diagonally.");
        JLabel instructStandard = new JLabel("Standard Gomoku requires exactly five in a row to win.");
        JLabel instructFreestyle = new JLabel("Freestyle Gomoku allows five or more stones in a row to win.");
        JLabel instructCheck = new JLabel("Default game is Standard. Change to Freestlye by checking the appropriate Settings box on the Home Screen.");
        panel.add(instruct);
        panel.add(instructTwo);
        panel.add(instructThree);
        panel.add(instructStandard);
        panel.add(instructFreestyle);
        panel.add(instructCheck);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(BorderLayout.CENTER, panel);
        
    }
}
