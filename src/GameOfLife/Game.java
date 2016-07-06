package GameOfLife;

import java.awt.Dimension;
import java.awt.Toolkit;
import SwingGUI.Frame;

public class Game
{
	static int width;
	static int height;
	static float PAUSETIME = 0.05f;

	private Frame frame;
	
	public Game()
	{
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenDim.width;
		height = screenDim.height;
	}
	
	public void CreateAndShowGUI()
	{
		frame = new Frame("Game of Life");
		frame.setVisible(true);
		frame.setResizable(true);
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