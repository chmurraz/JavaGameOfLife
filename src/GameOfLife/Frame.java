package GameOfLife;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame
{
	
	private Screen s;
	private Blob blob;
	//private Simulation sim;
	
	private class Screen extends JLabel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//sim.Draw(g);
			blob.Draw(true, g);
		}
	}
	
	public Frame()
	{
		blob = new Blob();
		s = new Screen();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(new Dimension(Game.width/2,Game.height));
		s.setBounds(0,0,Game.width,Game.height);
		add(s);
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
		s.repaint();
	}
	
	public Blob getBlob()
	{
		return blob;
	}
	
}
