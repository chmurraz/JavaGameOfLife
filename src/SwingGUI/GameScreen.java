package SwingGUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import GameOfLife.Blob;
import GameOfLife.Cell;
import GameOfLife.IntPoint2D;

public class GameScreen extends JPanel
{
	private Blob blob;
	private Cell centroid;
	
	public GameScreen()
	{
		blob = new Blob();
		centroid = new Cell(0,0);
		setBackground(Color.GREEN);
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
			if(blob.getDrawCentroid())
			{
				IntPoint2D point = new IntPoint2D((int)blob.getCentroid().getX(),(int)blob.getCentroid().getY());
				Cell centroid = new Cell(point);
				centroid.setColor(Color.RED);
				centroid.Draw(g);
			
				//	Java's drawOval has the first two parameters being the upper left corner, not the center.
				//	use an offset by half the radius (six sigma) to adjust for this
				
				int sigma = (int)Math.pow(blob.getVarianceCentroidDistance(),0.5);
				
				//	NEED TO MULTIPLY THIS BY CELL SIZE
				int x = (centroid.getIntPoint().getX())*Cell.getSize();
				int y = centroid.getIntPoint().getY()*Cell.getSize();
				
				int average = (int)blob.getAverageCentroidDistance();
				int radius = average;
				g.setColor(Color.RED);
				g.drawOval(x - radius, y - radius, 2*radius, 2*radius);
				
				g.setColor(Color.GREEN);
				radius = Math.max(0, average + 3*sigma*Cell.getSize());
				g.drawOval(x - radius, y - radius, 2*radius, 2*radius);
				
				g.setColor(Color.BLUE);
				radius = Math.max(0, average + 6*sigma*Cell.getSize());
				g.drawOval(x - radius, y - radius, 2*radius, 2*radius);
			}
		}
	}
	
	public Blob getBlob()
	{
		return blob;
	}
}
