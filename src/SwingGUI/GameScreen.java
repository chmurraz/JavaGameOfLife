package SwingGUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import GameOfLife.Blob;
import GameOfLife.Cell;
import GameOfLife.IntPoint2D;

public class GameScreen extends JLabel
{
	private Blob blob;
	private Cell centroid;
	
	public GameScreen()
	{
		blob = new Blob();
		centroid = new Cell(0,0);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(blob.getLiveCellCount()>0)
		{
			//	Draw the blob
			blob.Draw(g);
			
			//	Draw its centroid
			IntPoint2D point = new IntPoint2D((int)blob.getCentroid().getX(),(int)blob.getCentroid().getY());
			
			centroid.setPoint(point);
			g.setColor(Color.RED);
			centroid.Draw(g);
		}
	}
	
	public Blob getBlob()
	{
		return blob;
	}
}
