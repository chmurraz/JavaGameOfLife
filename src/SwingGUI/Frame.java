package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
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
	private UserPanel userPanel;
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
		
		//	Set size of "this" Frame
		setSize(new Dimension(Game.getWidth()/2,Game.getHeight()));
		
		//	Set size of the gameScreen
		gameScreen.setBounds(0,0,Game.getWidth()/2,Game.getHeight()/2);
		add(gameScreen);
		
		userPanel = new UserPanel();
		
		userPanel.addUserListener(new UserListener ()
		{
			public void detailEventOccured(UserEvent event)
			{
				//	Get the text from the event
				//String text = event.getText();
				
				//	Append this text into the text panel
				//textArea.append(text);
			}
		});
		
		//	Add Swing components to content pane
		Container c = getContentPane();
		
		//c.add(textArea, BorderLayout.CENTER);
		c.add(gameScreen, BorderLayout.CENTER);
		c.add(userPanel,BorderLayout.WEST);
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
