package SwingGUI;

import java.awt.Graphics;

import javax.swing.JLabel;

import GameOfLife.Blob;

public class GameScreen extends JLabel
{
	private Blob blob;
	
	public GameScreen()
	{
		blob = new Blob();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(blob.getLiveCellCount()>0)
		{
			blob.Draw(g);
		}
		//blob.UpdateBlob();
	}
	
	public Blob getBlob()
	{
		return blob;
	}
}
