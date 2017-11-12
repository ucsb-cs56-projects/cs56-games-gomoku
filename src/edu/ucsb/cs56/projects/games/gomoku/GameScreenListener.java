package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreenListener implements ActionListener {
    
    public void actionPerformed(ActionEvent event){
        
        //    Create a timer.
        //Timer mainProgramTimer = new Timer();
        //    Create a new instance of the program
        Gomoku panel = new Gomoku();
        
        //     Set up the settings of our JFrame
        
        //panel.mainProgramTimer = new Timer();
        
        panel.playStandard = !Viewer.setPlayFreestyle;
        
        //    Set size of window
        panel.frame.setSize(panel.getScreenWidth(), panel.getScreenHeight());
        
        //  Set the title
        panel.frame.setTitle("Gomoku");
        
        //    Things should happen in the panel
        panel.frame.setContentPane(panel);
        
        //panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this is the only part of Eric Huang's original code commented out; we want exit to be tied to home screen, not game screen.
        
        //    Make window visible
        panel.frame.setVisible(true);
        
        //    Do this regularly
        //panel.mainProgramTimer.schedule(panel.gomokuTask, 0, 20);
    }
}
