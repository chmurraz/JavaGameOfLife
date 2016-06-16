package GameOfLife;

public class CellChris
{

	private IntPoint2D point = new IntPoint2D();
	private int neighborCount;
	private Boolean isAlive;
	
	public CellChris(IntPoint2D pointVal)
	{
		point = pointVal;
		neighborCount = 0;
		isAlive = true;
	}
	
	public CellChris(int xVal, int yVal)
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