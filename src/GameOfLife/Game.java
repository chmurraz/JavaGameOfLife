package GameOfLife;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Game
{
	static int width;
	static int height;
	static float PAUSETIME = 0.05f;
	
	private long lastFrameTime;
	private long thisFrameTime;
	private float tslf;
	private float tslu;
	
	private Frame frame;
	
	public Game()
	{
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenDim.width;
		height = screenDim.height;
		frame = new Frame();
		frame.setVisible(true);
		frame.setResizable(false);
		tslu = 0;
		tslf = System.currentTimeMillis();
		frame.getBlob().BuildRandom(0.20);
	}
	
	public void Run()
	{
		lastFrameTime = System.currentTimeMillis();
		
		while(true)
		{
			thisFrameTime = System.currentTimeMillis();
			tslf = (float)((thisFrameTime-lastFrameTime)/1000.0);
			lastFrameTime = thisFrameTime;
			
			getFrame().getBlob().UpdateBlob();
			getFrame().Repaint();
			
			try
			{
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public Frame getFrame()
	{
		return frame;
	}
	
}
