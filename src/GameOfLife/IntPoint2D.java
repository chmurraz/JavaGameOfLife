package GameOfLife;

public class IntPoint2D
{
	private int x;
	private int y;
	
	public IntPoint2D()
	{
		x = 0;
		y = 0;
	}
	
	public IntPoint2D(int xval, int yval)
	{
		x = xval;
		y = yval;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setxy(int xval, int yval)
	{
		x = xval;
		y = yval;
	}
	
	public int NeighborDistance(IntPoint2D other)
	{
		int x = Math.abs(this.getX()-other.getX());
		int y = Math.abs(this.getY()-other.getY());
		
		return Math.max(x, y);
	}
	
	public double Distance(IntPoint2D other)
	{
		int a = this.getX() - other.getX();
		int b = this.getY() - other.getY();
		
		double a2 = Math.pow(a, 2);
		double b2 = Math.pow(b, 2);
		return Math.pow(a2 + b2, 0.5);
	}
				
	public Boolean Equal(IntPoint2D other)
	{
		if(this.getX() == other.getX() && this.getY() == other.getY())
		{
			return true;
		}
		return false;
	}
}
