package GameOfLife;

import java.awt.Graphics;
import java.util.ArrayList;

public class Blob 
{
	private ArrayList<Cell> cellsInGame;
	private int liveCellCount;
	private IntPoint2D plotmin;
	private IntPoint2D plotmax;
	private BlobBoundaries boundary;
	private int age;

	public class BlobBoundaries
	{
		private int minx;
		private int maxx;
		private int miny;
		private int maxy;
		
		public BlobBoundaries()
		{
			minx = Integer.MAX_VALUE;
			maxx = Integer.MIN_VALUE;
			miny = Integer.MAX_VALUE;
			maxy = Integer.MIN_VALUE;
		}
		
		public int getMinX()
		{
			return minx;
		}
		
		public void setMinX(int val)
		{
			minx = val;
		}
		
		public int getMaxX()
		{
			return maxx;
		}
		
		public void setMaxX(int val)
		{
			maxx = val;
		}
		
		public int getMinY()
		{
			return miny;
		}
		
		public void setMinY(int val)
		{
			miny = val;
		}
		
		public int getMaxY()
		{
			return maxy;
		}
		
		public void setMaxY(int val)
		{
			maxy = val;
		}
	}
	
	private void BuildDeadCells()
	{
		//	If there aren't any live cells, don't bother adding dead ones
		if(liveCellCount > 0)
		{
			//	Create a copy of the (cellsInGame) vector to add the dead CellChriss to
			ArrayList<Cell>cellsInGameCopy = new ArrayList<Cell>();
			for(Cell it:cellsInGame)
			{
				IntPoint2D newPoint = it.getIntPoint();
				Cell newCell = new Cell(newPoint);
				cellsInGameCopy.add(newCell);				
			}
			
			//	At this point, cellsInGameCopy should have an identical set of cells.
			//	Iterate over this copy and add dead cells to the ORIGINAL array (but only if)
			//	there isn't already a cell there
			
			for (Cell it:cellsInGameCopy)
			{
				IntPoint2D centerPoint = it.getIntPoint();
				for(int x = centerPoint.getX()-1; x<=centerPoint.getX()+1; x++)
				{
					for(int y = centerPoint.getY()-1; y<=centerPoint.getY()+1; y++)
					{
						//	Search the list of cells.  Check for a matching (x,y)...doesn't matter if it's alive or dead
						IntPoint2D testPoint = new IntPoint2D(x,y);
						if(!this.IsCellHere(testPoint))
						{
							//	Create a new cell at this (x,y) location
							//	add it to the ArrayList of cellsInGame
							//	make sure this cell is dead
							
							Cell newCell = new Cell(testPoint);
							cellsInGame.add(newCell);
							cellsInGame.get(cellsInGame.size()-1).setIsAlive(false);

						}
					}
				}
			}
		}
	}
	
	public Blob()
	{
		
		liveCellCount = 0;
		cellsInGame = new ArrayList<Cell>();
		
		//	Declare an array hold the minx, maxx, miny and maxy values of all cells
		boundary = new BlobBoundaries();
		
		plotmin = new IntPoint2D();
		plotmax = new IntPoint2D();
		plotmin.setxy(0,0);
		plotmax.setxy(100,100);
		
		age=0;
	}
	
	public void AddLiveCell(IntPoint2D point)
	{
		Cell addingCell = new Cell(point);
		Boolean cellExistsAtPoint = false;
		for(Cell c:this.getCellsInGame())
		{
			//	Check if there's a live cell at this point
			if(c.getIntPoint().Equal(point) && c.getIsAlive())
			{
				cellExistsAtPoint = true;
			}
		}
		if (!cellExistsAtPoint)
		{
			cellsInGame.add(addingCell);
			UpdateBoundary(point);
		}
	}
	
	public void UpdateLiveCellCount()
	{
		liveCellCount = 0;
		for(Cell c:this.getCellsInGame())
		{
			if(c.getIsAlive())
			{
				liveCellCount++;
			}
		}
	}
	
	public void UpdateBoundary(IntPoint2D point)
	{
		if (point.getX() < boundary.getMinX())
		{
			boundary.setMinX(point.getX());
		}
		
		if (point.getX() > boundary.getMaxX())
		{
			boundary.setMaxX(point.getX());
		}
		
		if (point.getY() < boundary.getMinY())
		{
			boundary.setMinY(point.getY());
		}
		
		if (point.getY() > boundary.getMaxY())
		{
			boundary.setMaxY(point.getY());
		}
	}
	
	public void BirthDeath()
	{
		for(Cell it:cellsInGame)
		{
			if(it.getIsAlive() && (it.getNeighborCount()<2 || it.getNeighborCount()>3))
			{
				it.setIsAlive(false);
				it.setAge(-1);
			}
			if(!it.getIsAlive() && it.getNeighborCount() == 3)
			{
				it.setIsAlive(true);
				it.setAge(0);
			}
			if(it.getIsAlive() && it.getNeighborCount() == 3)
			{
				it.setAge(it.getAge() + 1);	
			}
		}
	}
	
	public void BuildGlider()
	{
		this.AddLiveCell(new IntPoint2D(20,5));
		this.AddLiveCell(new IntPoint2D(21,5));
		this.AddLiveCell(new IntPoint2D(22,5));
		this.AddLiveCell(new IntPoint2D(22,6));
		this.AddLiveCell(new IntPoint2D(21,7));
		UpdateLiveCellCount();
	}
	
	public void BuildOscillator()
	{
		int xshift = 40;
		int yshift = 50;
		this.AddLiveCell(new IntPoint2D(10 + xshift,1 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,2 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,3 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,4 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,5 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,6 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,7 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,8 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,9 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,10 + yshift));
		UpdateLiveCellCount();
	}
	
	public void BuildBlock()
	{
		for (int i = 0; i<=85; i++)
		{
			for (int j = 0; j<=85; j++)
			{
				this.AddLiveCell(new IntPoint2D(30+i,30+j));
			}
		}
		UpdateLiveCellCount();
	}
	
	public void BuildRandom(double density)
	{
		if (density > 1.0)
		{
			density = 1.0;
		}
		if (density < 0.0)
		{
			density = 0.0;
		}
		
		int count = (int)Math.floor(density * (plotmax.getX() - plotmin.getX()) * (plotmax.getY()-plotmin.getY()));
		
		Dice XDice = new Dice(plotmax.getX() - plotmin.getX() + 1);
		Dice YDice = new Dice(plotmax.getY() - plotmin.getY() + 1);
		
		IntPoint2D randomPoint;
		for (int i = 1; i <= count; i++)
		{
			do
			{
				randomPoint = new IntPoint2D();
				randomPoint.setxy(XDice.roll() + plotmin.getX() - 1, YDice.roll() + plotmin.getY() - 1);
			}while(this.IsCellHere(randomPoint));
			this.AddLiveCell(randomPoint);
		}
		UpdateLiveCellCount();
		
	}
	
	public void BuildMethuselah()
	{
		
		int xshift = 10;
		int yshift = 10;
		this.AddLiveCell(new IntPoint2D(10 + xshift,1 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,2 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift,1 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift,2 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,6 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,8 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift,7 + yshift));
		this.AddLiveCell(new IntPoint2D(12 + xshift,7 + yshift));
		UpdateLiveCellCount();
	}
	
	public void BuildAcorn()
	{
		int xshift = 76;
		int yshift = 100;
		this.AddLiveCell(new IntPoint2D(10 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(13 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(14 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(15 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(16 + xshift, 0 + yshift));
		UpdateLiveCellCount();
	}
	
	public void BuildKokGalaxy()
	{
		int xshift = 50;
		int yshift = 100;
		
		this.AddLiveCell(new IntPoint2D(1 + xshift, 3 + yshift));
		this.AddLiveCell(new IntPoint2D(1 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(1 + xshift, 8 + yshift));

		this.AddLiveCell(new IntPoint2D(2 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 7 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 8 + yshift));
		
		this.AddLiveCell(new IntPoint2D(3 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(3 + xshift, 9 + yshift));
		
		this.AddLiveCell(new IntPoint2D(4 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(4 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(4 + xshift, 8 + yshift));
		
		this.AddLiveCell(new IntPoint2D(6 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(6 + xshift, 8 + yshift));
		this.AddLiveCell(new IntPoint2D(6 + xshift, 9 + yshift));
		
		this.AddLiveCell(new IntPoint2D(7 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(7 + xshift, 8 + yshift));
		
		this.AddLiveCell(new IntPoint2D(8 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(8 + xshift, 3 + yshift));
		this.AddLiveCell(new IntPoint2D(8 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(8 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(8 + xshift, 8 + yshift));
		this.AddLiveCell(new IntPoint2D(8 + xshift, 9 + yshift));
		
		this.AddLiveCell(new IntPoint2D(9 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(9 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(9 + xshift, 7 + yshift));

		
		UpdateLiveCellCount();
	}
	
	public void CountNeighbors()
	{
		for (Cell inputCell:cellsInGame)
		{
			//	Loop through the ArrayList of cellsInGame.  These are the "it" cells.  Compare them to the "inputCell" from outer loop
			for (Cell it:cellsInGame)
			{
				//	Check two things at each loop....
				//
				//		1.  Is the distance exactly one between "it" and "inputCell"?
				//		2.	Is the "it" cell alive?  It does not matter if the inputCell is alive or dead.  Only if "it" is alive.
				
				//	If both criteria are true, then "it" is a neighbor of "inputCell" (even if the inputCell itself is dead).
				//	Increment the counter for inputCell
				
				if(it.getIntPoint().NeighborDistance(inputCell.getIntPoint()) == 1 && it.getIsAlive())
				{
					inputCell.setNeighborCount(inputCell.getNeighborCount() + 1);
				}
			}
		}
	}
	
	public void Draw(Graphics g)
	{
		for (Cell it:cellsInGame)
		{
			it.Draw(g);
		}
	}
	
	public Boolean IsCellHere(IntPoint2D point)
	{
		//	Search the list of cells.  Check for a matching (x,y) at "point" ... doesn't matter if it's alive or dead.
		for (Cell it:cellsInGame)
		{
			if(it.getIntPoint().Equal(point))
			{
				return true;
			}
		}
		return false;
	}
	
	public void ResetBlobStats()
	{
		//	Make sure to reset all vital statistics for each cell and the blob as a whole...
		
		//	A copy of the ArrayList "cellsInGame" is created and filled with the living cells from the original ArrayList
		//	This is done so that unnecessary "dead" cells included in the ArrayList can be eliminated.  A new set of dead
		//	cells will be built during the update.
		
		liveCellCount = 0;
		
		boundary.setMaxX(Integer.MIN_VALUE);
		boundary.setMinX(Integer.MAX_VALUE);
		boundary.setMaxY(Integer.MIN_VALUE);
		boundary.setMinY(Integer.MAX_VALUE);
		
		ArrayList<Cell> cellsInGameCopy = new ArrayList<Cell>();
		
		for (Cell it:cellsInGame)
		{
			if(it.getIsAlive())
			{
				liveCellCount++;
				IntPoint2D newPoint = new IntPoint2D(it.getIntPoint().getX(),it.getIntPoint().getY());
				Cell newCell = new Cell(newPoint);
				newCell.setAge(it.getAge());
				UpdateBoundary(newPoint);
				cellsInGameCopy.add(newCell);
			}
			it.setNeighborCount(0);
		}
		cellsInGame = cellsInGameCopy;
	}
	
	public void UpdateBlob()
	{
		//	Reset vital stats
		ResetBlobStats();
		
		//	Fill the blob with dead cells around every living cell (making sure to avoid any adjacent living cells)
		BuildDeadCells();

		//	Calculate neighbors
		CountNeighbors();

		//	Add any newly spawned cells and remove dying cells from the blob and update cell ages
		BirthDeath();

		//	Increment age of the blob
		age++;
		
		//	Update liveCell count
		UpdateLiveCellCount();
	}
	
	public int getAge()
	{
		return age;
	}
	
	public int getLiveCellCount()
	{
		return liveCellCount;
	}
	
	public BlobBoundaries getBoundary()
	{
		return boundary;
	}
	
	public ArrayList<Cell> getCellsInGame()
	{
		return cellsInGame;
	}
}
	
