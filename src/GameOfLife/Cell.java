package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;

public class Cell
{
	private int x;
	private int y;
	private boolean alive;
	private boolean nextRound;
	static int size = 5;
	
	public Cell(int xVal, int yVal)
	{
		this.x = xVal;
		this.y = yVal;
	}
	
	public void Draw(Graphics g)
	{
		if(alive) g.setColor(Color.BLACK);
		else g.setColor(Color.WHITE);
		g.drawRect(x*size, y*size, size, size);
		//g.drawOval(x*size, y*size, size, size);
		g.fillRect(x*size + 1, y*size + 1, size - 1, size - 1);		
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public void setNextRound(boolean alive)
	{
		this.nextRound = alive;
	}
	
	public void NextRound()
	{
		alive = nextRound;
	}
}
