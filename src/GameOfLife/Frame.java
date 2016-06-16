package GameOfLife;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame
{
	private Screen s;
	//private Simulation sim;
	private float tslu;
	private float PAUSETIME = 0.05f;
	
	public Frame()
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screen.width/2,screen.height/2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void CreateScreen()
	{
		s = new Screen();
		//sim = new Simulation();
		s.setBounds(0,0,Main.width,Main.height);
		add(s);
	}
	
	//	tslf = time since last frame
	public void Update(float tslf)
	{
		tslu += tslf;
		if(tslu > PAUSETIME)
		{
			//sim.Update();
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
