package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame
{
	private Screen s;
	private Simulation sim;
	private float tslu;
	private float PAUSETIME = 0.05f;
	
	public Frame()
	{
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void CreateScreen()
	{
		s = new Screen();
		sim = new Simulation();
		s.setBounds(0,0,Main.width,Main.height);
		add(s);
	}
	
	//	tslf = time since last frame
	public void Update(float tslf)
	{
		tslu += tslf;
		if(tslu > PAUSETIME)
		{
			sim.Update();
			tslu = 0;
		}
	}
	
	public void Repaint()
	{
		s.repaint();
	}
	
	private class Screen extends JLabel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			sim.Draw(g);

		}
	}
}
