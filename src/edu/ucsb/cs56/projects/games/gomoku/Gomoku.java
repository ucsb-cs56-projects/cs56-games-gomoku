package edu.ucsb.cs56.projects.games.gomoku;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Gomoku extends JPanel{
	
	//General variables, such as screen size etc.
	private Rectangle screen;	
	public JFrame frame;
	private Point boardSize;
	private int tileSize;
	public gomokuTimerTask gomokuTask;
	private Random random;
	
	//Game
	private int currentColor;	//Either 1 or 2
	private int[][] grid;
	
	//Colors
	private final Color player1Color = new Color(0,200,0);
	private final Color player2Color = new Color(0,0,200);
	private final Color emptyColor = new Color(200,200,200);
	
	public Gomoku(){
		
		super();
		
		//	General variables
		gomokuTask = new gomokuTimerTask();
		boardSize = new Point(70,40);
		grid = new int[boardSize.x][boardSize.y];
		for(int x = 0; x<boardSize.x;x++){
			for(int y = 0;y<boardSize.y;y++){
				grid[x][y] = 0;
			}
		}
		tileSize = 20;
		int screenHeight = boardSize.y*tileSize;
		int screenWidth = boardSize.x*tileSize;
		screen = new Rectangle(0, 0, screenWidth, screenHeight);
		frame = new JFrame("Gomoku");
		random = new Random();
		
		//Game
		currentColor = 1;

		//Add listeners that keep track of the mouse 
		addMouseListener(this);
	}
	
	class gomokuTimerTask extends TimerTask{ 
		//	Main loop, done every iteration.
		public void run(){
			
			//Repaint
			frame.repaint();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		
		
		for(int x = 0;x<gomo.getBoardSize().x;x++)
		{
			for(int y = 0; y<gomo.getBoardSize().y;y++)
			{
				if(gomo.getGrid(x,y) == 1)
				{
					g.setColor(gomo.getPlayer1Color());
				}
				else if(gomo.getGrid(x,y)== 2)
				{
					g.setColor(gomo.getPlayer2Color());
				}
				else
				{
					g.setColor(gomo.getEmptyColor());
				}
				
				g.fillOval(x*tileSize, y*tileSize, tileSize, tileSize);
			}
		}
	}
	
	//	All functions need to be written (even if they are empty) in order to comply with the MouseListener interface
	public void mouseClicked(MouseEvent mouse){
		grid[mouse.getX()/tileSize][mouse.getY()/tileSize] = currentColor;
		//Switch player
		if(currentColor == 1){
			currentColor = 2;
		}else{
			currentColor = 1;
		}
    }
	public void mouseEntered(MouseEvent mouse){ 
	}   
    public void mouseExited(MouseEvent mouse){
    }
    public void mousePressed(MouseEvent mouse){
    }
    public void mouseReleased(MouseEvent mouse){ 
    }
    public void mouseDragged(MouseEvent mouse){	
    }
    public void mouseMoved(MouseEvent mouse){
    }

	public Point getBoardSize()
	{
		return this.boardSize;
	}
	
	public int getGrid(int x, int y)
	{
		return this.grid[x][y];
	}
	
	public int setGrid(int x, int y)
	{
		grid[x][y]
	}
	
	public Color getPlayer1Color()
	{
		return this.player1Color;
	}

	public Color getPlayer2Color()
	{
		return this.player2Color;
	}
	
	public Color getEmptyColor()
	{
		return this.emptyColor;
	}
	
	public int getTileSize()
	{
		return this.tileSize;
	}
	
	public int getScreenWidth()
	{
		return (int)(screen.getWidth());
	}
	
	public int getScreenHeight()
	{
		return (int)(this.screen.getHeight());
	}
}
