package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;

public class Cell
{

	private IntPoint2D point = new IntPoint2D();
	private int neighborCount;
	private Boolean isAlive;
	static int size = 5;
	
	public void Draw(Graphics g)
	{
		if (isAlive)
		{
			g.setColor(Color.RED);
			g.fillRect(point.getX()*size, point.getY()*size, size, size);
		}
	}
	
	public Cell(IntPoint2D pointVal)
	{
		point = pointVal;
		neighborCount = 0;
		isAlive = true;
	}
	
	public Cell(int xVal, int yVal)
	{
		point.setxy(xVal, yVal);
		neighborCount = 0;
		isAlive = true;
	}

	public void setPoint(IntPoint2D pointVal)
	{
		point = pointVal;
	}
	
	public void setNeighborCount(int intVal)
	{
		neighborCount = intVal;
	}
	
	public int getNeighborCount()
	{
		return neighborCount;
	}
	
	public void setIsAlive(Boolean boolVal)
	{
		isAlive = boolVal;
	}
	
	public Boolean getIsAlive()
	{
		return isAlive;
	}
	
	public IntPoint2D getIntPoint()
	{
		return point;
	}
}