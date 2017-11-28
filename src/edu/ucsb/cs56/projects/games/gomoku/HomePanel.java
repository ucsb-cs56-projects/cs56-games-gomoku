package edu.ucsb.cs56.projects.games.gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

public class HomePanel extends JPanel {
    public HomePanel ( ) {
    
        //Set title for this screen.
        JLabel title = new JLabel("Gomoku");
        Font titleFont = new Font("sans_serif", Font.BOLD, 28);
        title.setFont(titleFont);
    
    
        //When clicked, this button takes user to game screen.
        JButton getGameButton = new JButton("Play!");
        getGameButton.addActionListener((x)-> { Viewer.showGamePanel(); });
        //When clicked, this button takes user to instructions.
        JButton getInstructionsButton = new JButton("Instructions");
        getInstructionsButton.addActionListener((x)-> { Viewer.showInstructionsPanel(); });
        
        JCheckBox check = new JCheckBox("Check to change to Freestyle Gomoku.");
        check.addItemListener(new ItemListener() {
        
            public void itemStateChanged(ItemEvent e){
                if(check.isSelected()){
                    Viewer.setPlayFreestyle = true;
                }
                if(!check.isSelected()){
                    Viewer.setPlayFreestyle = false;
                }
            }
        
        });
    
        JLabel SettingsTitle = new JLabel("\n"+ "Settings:" + "\n");
        
        
        JPanel OptionsPanel = new JPanel();
        JPanel SettingsPanel = new JPanel();
    
        OptionsPanel.add(getGameButton);
        OptionsPanel.add(getInstructionsButton);
    
        SettingsPanel.add(SettingsTitle);
        SettingsPanel.add(check);
    
        OptionsPanel.setLayout(new BoxLayout(OptionsPanel, BoxLayout.Y_AXIS));
        SettingsPanel.setLayout(new BoxLayout(SettingsPanel, BoxLayout.Y_AXIS));
        
        this.add(BorderLayout.EAST, OptionsPanel);
        this.add(BorderLayout.NORTH, title);
        this.add(BorderLayout.CENTER, SettingsPanel);
    }
}
