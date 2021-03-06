package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Blob 
{
	private ArrayList<Cell> cellsInGame;
	private int liveCellCount;
	private IntPoint2D plotmin;
	private IntPoint2D plotmax;
	private Centroid centroid;
	private double averageCentroidDistance;
	private double varianceCentroidDistance;
	private BlobBoundaries boundary;
	private int age;
	private Boolean removeSixSigma;
	private Boolean drawCentroid;
	private int escapedCells;
	private int xshift;
	private int yshift;

	public class Centroid
	{
		private double x;
		private double y;
		
		public Centroid()
		{
			x = 0.00;
			y = 0.00;
		}
		
		public double getX()
		{
			return x;
		}
		
		public double getY()
		{
			return y;
		}
		
		public void setX(double xVal)
		{
			x = xVal;
		}
		
		public void setY(double yVal)
		{
			y = yVal;
		}
	}
	
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
	
	public void BuildDeadCells()
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
		centroid = new Centroid();
		plotmin.setxy(0,0);
		plotmax.setxy(100,100);
		removeSixSigma = true;
		drawCentroid = false;
		
		age=0;
		escapedCells = 0;
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
	
	public void BuildClean()
	{
		for (Cell it:cellsInGame)
		{
			it.setIsAlive(false);
			age = 0;
		}
		UpdateLiveCellCount();
	}
	
	public void BuildGlider()
	{
		xshift = 40;
		yshift = 50;
		this.AddLiveCell(new IntPoint2D(20 + xshift,5 + yshift));
		this.AddLiveCell(new IntPoint2D(21 + xshift,5 + yshift));
		this.AddLiveCell(new IntPoint2D(22 + xshift,5 + yshift));
		this.AddLiveCell(new IntPoint2D(22 + xshift,4 + yshift));
		this.AddLiveCell(new IntPoint2D(21 + xshift,3 + yshift));
		UpdateLiveCellCount();
	}
	
	public void BuildOscillator()
	{
		xshift = 40;
		yshift = 50;
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
		xshift = 80;
		yshift = 80;
		for (int i = 0; i<=85; i++)
		{
			for (int j = 0; j<=85; j++)
			{
				this.AddLiveCell(new IntPoint2D(xshift + i, yshift + j));
			}
		}
		UpdateLiveCellCount();
	}
	
	public void BuildGliderGun()
	{
		xshift = 50;
		yshift = 200;
		this.AddLiveCell(new IntPoint2D(1 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(1 + xshift, 5 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(2 + xshift, 5 + yshift));
		
		this.AddLiveCell(new IntPoint2D(11 + xshift, 3 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift, 5 + yshift));
		
		this.AddLiveCell(new IntPoint2D(12 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(12 + xshift, 6 + yshift));
		
		this.AddLiveCell(new IntPoint2D(13 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(13 + xshift, 7 + yshift));
		this.AddLiveCell(new IntPoint2D(14 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(14 + xshift, 7 + yshift));
		
		this.AddLiveCell(new IntPoint2D(15 + xshift, 4 + yshift));
		
		this.AddLiveCell(new IntPoint2D(16 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(16 + xshift, 6 + yshift));
		
		this.AddLiveCell(new IntPoint2D(17 + xshift, 3 + yshift));
		this.AddLiveCell(new IntPoint2D(17 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(17 + xshift, 5 + yshift));
		
		this.AddLiveCell(new IntPoint2D(18 + xshift, 4 + yshift));
		
		this.AddLiveCell(new IntPoint2D(21 + xshift, 5 + yshift));
		this.AddLiveCell(new IntPoint2D(21 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(21 + xshift, 7 + yshift));
		
		this.AddLiveCell(new IntPoint2D(22 + xshift, 5 + yshift));
		this.AddLiveCell(new IntPoint2D(22 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(22 + xshift, 7 + yshift));
		
		this.AddLiveCell(new IntPoint2D(23 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(23 + xshift, 8 + yshift));
		
		this.AddLiveCell(new IntPoint2D(25 + xshift, 3 + yshift));
		this.AddLiveCell(new IntPoint2D(25 + xshift, 4 + yshift));
		this.AddLiveCell(new IntPoint2D(25 + xshift, 8 + yshift));
		this.AddLiveCell(new IntPoint2D(25 + xshift, 9 + yshift));
		
		this.AddLiveCell(new IntPoint2D(35 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(35 + xshift, 7 + yshift));
		this.AddLiveCell(new IntPoint2D(36 + xshift, 6 + yshift));
		this.AddLiveCell(new IntPoint2D(36 + xshift, 7 + yshift));



		
		UpdateLiveCellCount();
	}
	
	public void BuildRandom(double density)
	{
		xshift = 50;
		yshift = 70;
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
				randomPoint.setxy(XDice.roll() + plotmin.getX() - 1 + xshift, YDice.roll() + plotmin.getY() - 1 + yshift);
			}while(this.IsCellHere(randomPoint));
			this.AddLiveCell(randomPoint);
		}
		UpdateLiveCellCount();
		UpdateCentroidStats();
	}
	
	public void BuildMethuselah()
	{
		
		xshift = 10;
		yshift = 10;
		this.AddLiveCell(new IntPoint2D(10 + xshift,1 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,2 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift,1 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift,2 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,6 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift,8 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift,7 + yshift));
		this.AddLiveCell(new IntPoint2D(12 + xshift,7 + yshift));
		UpdateLiveCellCount();
		UpdateCentroidStats();
	}
	
	public void BuildAcorn()
	{
		xshift = 150;
		yshift = 100;
		this.AddLiveCell(new IntPoint2D(10 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(11 + xshift, 2 + yshift));
		this.AddLiveCell(new IntPoint2D(13 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(14 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(15 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(16 + xshift, 0 + yshift));
		UpdateLiveCellCount();
		UpdateCentroidStats();
	}
	
	public void BuildSimple()
	{
		xshift = 20;
		yshift = 20;
		this.AddLiveCell(new IntPoint2D(10 + xshift, 0 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift, 1 + yshift));
		this.AddLiveCell(new IntPoint2D(10 + xshift, 2 + yshift));
		UpdateLiveCellCount();
		UpdateCentroidStats();
	}
	
	public void BuildKokGalaxy()
	{
		xshift = 50;
		yshift = 100;
		
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
		UpdateCentroidStats();
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
		//	Create a copy of the cellsInGame ArrayList to iterate over
		//	This is to avoid the concurrency run time errors of changing the array
		//	while iterating over it.
		
		//ArrayList<Cell> copy = cellsInGame;
		
		//	The copy did not solve the concurrency run time error.
		
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
		
		//	Update the blob centroid statistics
		UpdateCentroidStats();
		
		//	Remove cells further than +/- 6 sigma from centroid.  Consider these cells to have escaped
		RemoveOutliers();
	}
	
	public void RemoveOutliers()
	{
		if (removeSixSigma)
		{
			for (Cell it:cellsInGame)
			{
				if(it.getIsAlive())
				{
					//	Get the distance of the cell from the centroid
					IntPoint2D point = it.getIntPoint();
					
					//	Calculate x^2 + y^2 and then the square root of that
					double distance = Math.pow(point.getX()-centroid.getX(), 2) + Math.pow(point.getY()-centroid.getY(), 2);
					distance = Math.pow(distance, 0.5);
					double sixSigma = 6*Math.pow(varianceCentroidDistance, 0.5);
					
					if (Math.abs(distance - averageCentroidDistance) > sixSigma)
					{
						it.setIsAlive(false);
						it.setColor(Color.RED);
						liveCellCount--;
						escapedCells++;
					}
				}
			}
		}
	}
	
	public void UpdateCentroidStats()
	{
		centroid.setX(0.00);
		centroid.setY(0.00);
		int n = liveCellCount;
		
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		for (Cell it:cellsInGame)
		{
			if(it.getIsAlive())
			{
				aliveCells.add(it);
			}
		}
		
		//	Calculate the centroid of the blob
		for (Cell it:aliveCells)
		{
			centroid.setX(centroid.getX() + (double)it.getIntPoint().getX()/n);
			centroid.setY(centroid.getY() + (double)it.getIntPoint().getY()/n);
		}
		
		//	Update the distances for each cell to the centroid
		averageCentroidDistance = 0;
		for (Cell it:aliveCells)
		{
			IntPoint2D point = it.getIntPoint();
			
			//	Calculate x^2 + y^2 and then the square root of that
			double distance = Math.pow(point.getX()-centroid.getX(), 2) + Math.pow(point.getY()-centroid.getY(), 2);
			distance = Math.pow(distance, 0.5);
			
			it.setCentroidDistance(distance);
			averageCentroidDistance += distance/n;
		}
		
		varianceCentroidDistance = 0;
		for(Cell it:aliveCells)
		{
			IntPoint2D point = it.getIntPoint();
			
			//	Calculate x^2 + y^2 and then the square root of that
			double distance = Math.pow(point.getX()-centroid.getX(), 2) + Math.pow(point.getY()-centroid.getY(), 2);
			distance = Math.pow(distance, 0.5);

			varianceCentroidDistance += Math.pow(distance - averageCentroidDistance, 2)/n;
		}
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int val)
	{
		age = val;
	}
	
	public int getLiveCellCount()
	{
		return liveCellCount;
	}
	
	public Centroid getCentroid()
	{
		return centroid;
	}
	
	public BlobBoundaries getBoundary()
	{
		return boundary;
	}
	
	public ArrayList<Cell> getCellsInGame()
	{
		return cellsInGame;
	}
	
	public int getEscapedCells()
	{
		return escapedCells;
	}
	
	public double getAverageCentroidDistance()
	{
		return averageCentroidDistance;
	}
	
	public double getVarianceCentroidDistance()
	{
		return varianceCentroidDistance;
	}
	
	public Boolean getDrawCentroid()
	{
		return drawCentroid;
	}
	
	public void setDrawCentroid(Boolean val)
	{
		drawCentroid = val;
	}
	
	public void setXShift(int val)
	{
		xshift = val;
	}
	
	public int getXShift()
	{
		return xshift;
	}
	
	public void setYShift(int val)
	{
		yshift = val;
	}
	
	public int getYShift()
	{
		return yshift;
	}
}
	
