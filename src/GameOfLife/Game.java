package GameOfLife;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.SwingWorker;

import SwingGUI.Frame;

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
		//frame = new Frame("Game of Life");
		//frame.setVisible(true);
		//frame.setResizable(false);
		tslu = 0;
		tslf = System.currentTimeMillis();
		//frame.getGameScreen().getBlob().BuildRandom(0.5);
		//frame.getGameScreen().getBlob().BuildGlider();
		//frame.getGameScreen().getBlob().BuildOscillator();
	}
	
	public void CreateAndShowGUI()
	{
		frame = new Frame("Game of Life");
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void Run()
	{
		while (true) 
		{
			lastFrameTime = System.currentTimeMillis();
			tslu = PAUSETIME * 2;
			
			while (true)
			{
				tslf = (float) ((thisFrameTime - lastFrameTime) / 1000.0);
				lastFrameTime = thisFrameTime;
				thisFrameTime = System.currentTimeMillis();

				//	tslf = time since last frame
				tslu += tslf;
				if (tslu > PAUSETIME) {
					//	Update the blob and repaint
					getFrame().getGameScreen().getBlob().UpdateBlob();
					getFrame().Repaint();

					//	Update the user panel
					int age = this.getFrame().getGameScreen().getBlob().getAge();
					int count = this.getFrame().getGameScreen().getBlob().getLiveCellCount();
					getFrame().getUserPanel().updateAgeLabel(age);
					getFrame().getUserPanel().updateCountLabel(count);

					tslu = 0;
				}

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		}
		
	}
	
	public Frame getFrame()
	{
		return frame;
	}
	
	public static int getHeight()
	{
		return height;
	}
	
	public static int getWidth()
	{
		return width;
	}
	
}
