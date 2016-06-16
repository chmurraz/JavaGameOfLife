package GameOfLife;

import java.awt.Graphics;
import java.util.Random;

public class Simulation
{
	private Cell[] [] cells;
	private Random random;
	private int width = Main.width/Cell.size;
	private int height = Main.height/Cell.size;
	private float weight = 0.50f;
	private float test;
	
	public Simulation()
	{
		random = new Random();
		cells = new Cell[width] [height];
		for (int x = 0; x < width; x++)
		{
			
			for (int y = 0; y < height; y++)
			{
				test = random.nextFloat();
				cells[x] [y] = new Cell(x,y);
				//cells[x] [y].setAlive(random.nextBoolean());
				
				if (test < weight)
					cells[x] [y].setAlive(true);
				else
					cells[x] [y].setAlive(false);
			}
		}
	}
	
	public void Update()
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				int mx = x-1;
				if (mx < 0) mx = width -1;
				int my = y - 1;
				if (my < 0) my = height -1;
				int gx= (x + 1) % width;
				int gy = (y+1) % height;
				
				int aliveCounter = 0;
				if(cells[mx] [my].isAlive()) aliveCounter++;
				if(cells[mx] [y].isAlive()) aliveCounter++;
				if(cells[mx] [gy].isAlive()) aliveCounter++;
				if(cells[x] [my].isAlive()) aliveCounter++;
				if(cells[x] [gy].isAlive()) aliveCounter++;
				if(cells[gx] [my].isAlive()) aliveCounter++;
				if(cells[gx] [y].isAlive()) aliveCounter++;
				if(cells[gx] [gy].isAlive()) aliveCounter++;
				
				if(aliveCounter < 2 || aliveCounter > 3) cells[x][y].setNextRound(false);
				else if(aliveCounter == 2) cells[x][y].setNextRound(cells[x][y].isAlive());
				if(aliveCounter == 3) cells[x][y].setNextRound(true);
			}
		}
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				cells[x][y].NextRound();
			}
		}
	}
	
	public void Draw(Graphics g)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				cells[x] [y].Draw(g);
			}
		}
	}
	
}
