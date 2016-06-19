package SwingGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import GameOfLife.Blob;
import GameOfLife.Game;

public class Frame extends JFrame
{
	//private Screen s;
	private GameScreen gameScreen;
	//private Blob blob;
	//private Simulation sim;
	
	/*
	private class Screen extends JLabel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			blob.Draw(g);
			blob.UpdateBlob();
		}
	}
	*/
	
	public Frame(String title)
	{
		super(title);
		//blob = new Blob();
		//s = new Screen();
		gameScreen = new GameScreen();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(new Dimension(Game.getWidth()/2,Game.getHeight()));
		gameScreen.setBounds(0,0,Game.getWidth()/2,Game.getHeight()/2);
		add(gameScreen);
	}
	
	
	//	Is CreateScreen() necessary?
	/*
	public void CreateScreen()
	{
		s = new Screen();
		//sim = new Simulation();
		//blob = new Blob();
		s.setBounds(0,0,Main.width,Main.height);
		add(s);
	}
	*/
	
	public void Repaint()
	{
		gameScreen.repaint();
	}
	
	/*
	public Blob getBlob()
	{
		return blob;
	}
	*/
	
	public GameScreen getGameScreen()
	{
		return gameScreen;
	}
	
}
