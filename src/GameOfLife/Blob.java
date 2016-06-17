package GameOfLife;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.lang.Math;
import javax.swing.JFrame;

public class Blob 
{
	private ArrayList<CellChris> cellsInGame;
	private int liveCellCount;
	private IntPoint2D plotmin;
	private IntPoint2D plotmax;
	private int age;

	
	private void BuildDeadCells()
	{
		//	If there aren't any live cells, don't bother adding dead ones
		if(liveCellCount > 0)
		{
			//	Create a copy of the (cellsInGame) vector to add the dead CellChriss to
			ArrayList<CellChris>cellsInGameCopy = new ArrayList<CellChris>();
			for(CellChris it:cellsInGame)
			{
				IntPoint2D newPoint = it.getIntPoint();
				CellChris newCell = new CellChris(newPoint);
				cellsInGameCopy.add(newCell);				
			}
			
			//	At this point, cellsInGameCopy should have an identical set of cells.
			//	Iterate over this copy and add dead cells to the ORIGINAL array (but only if)
			//	there isn't already a cell there
			
			for (CellChris it:cellsInGameCopy)
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
							
							CellChris newCell = new CellChris(testPoint);
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
		cellsInGame = new ArrayList<CellChris>();
		
		plotmin = new IntPoint2D();
		plotmax = new IntPoint2D();
		plotmin.setxy(0,0);
		plotmax.setxy(50,50);
		
		age=0;
		
		//frame.setSize(800,600);
		//frame.setTitle("Hey Guy!");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void AddLiveCell(IntPoint2D point)
	{
		CellChris addingCell = new CellChris(point);
		cellsInGame.add(addingCell);
		liveCellCount++;
	}
	
	public void BirthDeath()
	{
		for(CellChris it:cellsInGame)
		{
			if(it.getNeighborCount()<2 || it.getNeighborCount()>3)
			{
				it.setIsAlive(false);
			}
			if(!it.getIsAlive() && it.getNeighborCount() == 3)
				it.setIsAlive(true);
		}
	}
	
	public void BuildGlider()
	{
		int xshift = 10;
		int yshift = 20;
		
		this.AddLiveCell(new IntPoint2D(20+xshift,0+yshift));
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
		
		int count = (int)Math.floor(density * plotmax.getX() * plotmax.getY());
		
		Dice XDice = new Dice(plotmax.getX());
		Dice YDice = new Dice(plotmax.getY());
		
		IntPoint2D randomPoint;
		for (int i = 0; i <= count; i++)
		{
			do
			{
				randomPoint = new IntPoint2D();
				randomPoint.setxy(XDice.roll(), YDice.roll());
			}while(this.IsCellHere(randomPoint));
			this.AddLiveCell(randomPoint);
		}
		
	}
	
	public void CountNeighbors()
	{
		for (CellChris inputCell:cellsInGame)
		{
			//	Loop through the ArrayList of cellsInGame.  These are the "it" cells.  Compare them to the "inputCell" from outer loop
			for (CellChris it:cellsInGame)
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
	
	public void Draw()
	{
		//	Draw the rectangle on a "JComponent" and add the JComponent to the frame
		//frame.setVisible(false);
		for (CellChris it:cellsInGame)
		{
			it.Draw();
		}
		//CellGraphic DC = new CellGraphic();
		//frame.add(DC);
		//frame.setVisible(true);
	}
	
	public Boolean IsCellHere(IntPoint2D point)
	{
		//	Search the list of cells.  Check for a matching (x,y) at "point" ... doesn't matter if it's alive or dead.
		for (CellChris it:cellsInGame)
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
		ArrayList<CellChris> cellsInGameCopy = new ArrayList<CellChris>();
		
		for (CellChris it:cellsInGame)
		{
			if(it.getIsAlive())
			{
				liveCellCount++;
				IntPoint2D newPoint = new IntPoint2D(it.getIntPoint().getX(),it.getIntPoint().getY());
				CellChris newCell = new CellChris(newPoint);
				cellsInGameCopy.add(newCell);
			}
			it.setNeighborCount(0);
		}
		cellsInGame = cellsInGameCopy;
	}
	
	public void UpdateBlob(float tslf)
	{
		//	Reset vital stats
		ResetBlobStats();
		
		//	Fill the blob with dead cells around every living cell (making sure to avoid any adjacent living cells)
		BuildDeadCells();

		//	Draw the blob
		Draw();
		//frame.Update(tslf);
		//frame.Repaint();

		//	Calculate neighbors
		CountNeighbors();

		//	Add any newly spawned cells and remove dying cells from the blob
		BirthDeath();

		//	Increment age
		age++;
	}
}
	
