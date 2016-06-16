package GameOfLife;

public class Dice 
{
	private int sides;
	public Dice()
	{
		sides = 6;
	}
	
	public Dice(int sidesVal)
	{
		sides = sidesVal;
	}
	
	public int roll()
	{
		return (int)(Math.random() * sides + 1);
	}	
}
