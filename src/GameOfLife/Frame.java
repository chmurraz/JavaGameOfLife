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
			blob.Draw(true);
		}
	}
	
	public Frame()
	{
		blob = new Blob();
		s = new Screen();
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screenDim.width/2,screenDim.height/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		s.setBounds(0,0,Main.width, Main.height);
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
