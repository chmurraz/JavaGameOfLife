package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import GameOfLife.Blob.Centroid;
import GameOfLife.Cell;
import GameOfLife.IntPoint2D;

public class MyListener implements ActionListener
{
	private final Frame frame;
	private GridBagConstraints constraints;
	
	public MyListener(Frame frameVal)
	{
		frame = frameVal;
		constraints = new GridBagConstraints();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String AdvanceToggleCommand = frame.getUserPanel().getAdvanceToggleButton().getActionCommand();
		String LoadBlobButtonCommand = frame.getUserPanel().getLoadBlobButton().getActionCommand();
		String AutoRunToggleCommand = frame.getUserPanel().getAutoRunToggleButton().getActionCommand();
		String ShowCentroidToggleCommand = frame.getUserPanel().getShowCentroid().getActionCommand();
		String Tutorial = frame.getUserPanel().getTutorial().getActionCommand();
		String UpCellCommand = frame.getUserPanel().getUpCellSizeButton().getActionCommand();
		String DownCellCommand = frame.getUserPanel().getDownCellSizeButton().getActionCommand();
		String PanUp = frame.getUserPanel().getPanUpButton().getActionCommand();
		String PanLeft = frame.getUserPanel().getPanLeftButton().getActionCommand();
		String Recenter = frame.getUserPanel().getRecenterButton().getActionCommand();
		String PanRight = frame.getUserPanel().getPanRightButton().getActionCommand();
		String PanDown = frame.getUserPanel().getPanDownButton().getActionCommand();
		
		if(e.getActionCommand().equals(PanUp))
		{
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				int x = c.getIntPoint().getX();
				int y = c.getIntPoint().getY();
				c.getIntPoint().setxy(x, y - 1);
			}
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(PanLeft))
		{
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				int x = c.getIntPoint().getX();
				int y = c.getIntPoint().getY();
				c.getIntPoint().setxy(x - 1, y);
			}
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(Recenter))
		{
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(PanRight))
		{
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				int x = c.getIntPoint().getX();
				int y = c.getIntPoint().getY();
				c.getIntPoint().setxy(x + 1, y);
			}
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(PanDown))
		{
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				int x = c.getIntPoint().getX();
				int y = c.getIntPoint().getY();
				c.getIntPoint().setxy(x, y + 1);
			}
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(UpCellCommand))
		{
			if(Cell.getSize() < 9)
			{
				Cell.setSize(Cell.getSize() + 1);
			}
			else
			{
				Cell.setSize(10);
			}
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(DownCellCommand))
		{
			if(Cell.getSize() > 2)
			{
				Cell.setSize(Cell.getSize() - 1);
			}
			else
			{
				Cell.setSize(1);
			}
			frame.repaint();
		}
		
		if(e.getActionCommand().equals(AdvanceToggleCommand))
		{
			if(frame.getUserPanel().getAdvanceToggleButton().getText() == "Advance to Next Generation")
			{
				frame.getUserPanel().getAdvanceToggleButton().setText("Working...");
				frame.Execute();
			}
			else if(frame.getUserPanel().getAdvanceToggleButton().getText() == "Working...")
			{
				frame.getUserPanel().getAdvanceToggleButton().setText("Advance to Next Generation");
			}
		}
		
		if(e.getActionCommand().equals(ShowCentroidToggleCommand))
		{
			if(frame.getUserPanel().getShowCentroid().getText() == "Show Centroid: Off")
			{
				frame.getUserPanel().getShowCentroid().setText("Show Centroid: On");
				frame.getUserPanel().getCentroidLabel().setVisible(true);
				frame.getUserPanel().getAverageDistanceToCentroidLabel().setVisible(true);
				frame.getUserPanel().getVarianceDistanceToCentroid().setVisible(true);
				frame.getGameScreen().getBlob().setDrawCentroid(true);
				frame.repaint();
			}
			else if(frame.getUserPanel().getShowCentroid().getText() == "Show Centroid: On")
			{
				frame.getUserPanel().getShowCentroid().setText("Show Centroid: Off");
				frame.getUserPanel().getCentroidLabel().setVisible(false);
				frame.getUserPanel().getAverageDistanceToCentroidLabel().setVisible(false);
				frame.getUserPanel().getVarianceDistanceToCentroid().setVisible(false);
				frame.getGameScreen().getBlob().setDrawCentroid(false);
				frame.repaint();
			}
		}
		
		if(e.getActionCommand().equals(Tutorial))
		{
			CellBuildStartUp();
			frame.getGameScreen().getBlob().BuildClean();
			CellBuildCleanUp();
			
			String message = "This Java code (written by Chris Murray) implements a simulation of Conway's Game of Life.";
			message += "\nThe 'game' is a celluar automaton developed by mathematician John Conway in 1970.";
			message += "\nThe 'game' can be considered more of a simulation of cells under a microscope that appear to be 'alive' and exhibit interesting behavior.";
			message += "\nAlternatively, the 'game' can be thought of as a zero-player game.  Set up the starting conditions and press 'start' to see what the cells do.";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (1 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			message = "The game world consists of a group of 'cells' which are either alive or dead.";
			message += "\nThe cells will be displayed on the white section.  The yellow section controls various inputs and options.";
			message += "\nOnce a starting arrangement of cells is selected, the game rules determine if individual cells live or die and if new cells are born.";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (2 of 13)", JOptionPane.INFORMATION_MESSAGE);

			message = "The birth, death and survival of individual cells is determined by the number of neighbors a cell has.";
			message += "\nFor any given cell, another cell is considered a neighbor is it is both alive and located above, below, left, right or diagonally adjacent to the given cell.";
			message += "\nFor example, I will now draw a simple arrangement of three cells in a vertical line";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (3 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			CellBuildStartUp();
			Cell.setSize(10);
			frame.getGameScreen().getBlob().BuildSimple();
			CellBuildCleanUp();
			
			message = "I will now color the cells in an alternating red and black pattern to make them easier to tell apart";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (4 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			int i = 0;
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				if(c.getIsAlive())
				{
					i++;
					if(i == 1 || i == 3)
					{
						c.setColor(Color.RED);
					}
				}
			}
			frame.repaint();
			
			message = "This 'game' consists of the three living cells (two red and one black).";
			message += "\nEach red cell has only one neighbor (the black cell) and the black cell has two neighbors (the two red cells).";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (5 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			message = "In addition to the live cells, the 'game' tracks dead cells as well.  These dead cells are normally not displayed.";
			message += "\nHowever, for purposes of illustration, I will highlight the dead cells surrounding the red and black live cells.  These will be in green.";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (6 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			frame.getGameScreen().getBlob().BuildDeadCells();
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				if(!c.getIsAlive())
				{
					c.setColor(Color.GREEN);
					c.setIsAlive(true);
				}
			}
			frame.repaint();
			
			message = "The green (dead) cells in the top and bottom rows each have one neighbor (the red cells).";
			message += "\nThe green (dead) cells toward the top and bottom of the left and right columns have one neighbor.";
			message += "\nThe green (dead) cells in the center of the left and right columns have two neighbors.";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (7 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			message = "The rules for the Game of Life are, at each iteration:";
			message += "\n     1.   If a cell is alive and it has fewer than two live neighbors, it dies.";
			message += "\n     2.   If a cell is alive and it has two or three live neighors it lives to the next generation.";
			message += "\n     3.   If a cell is alive and it has more than three live neighbors, it dies.";
			message += "\n     4.   If a cell is dead and has three live neighbors it becomes a live cell.";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (8 of 13)", JOptionPane.INFORMATION_MESSAGE);

			message = "I will now remove the highlighting on the dead (green) cells and display only the living (red and black) cells";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (9 of 13)", JOptionPane.INFORMATION_MESSAGE);
			frame.getGameScreen().getBlob().BuildClean();
			frame.getGameScreen().getBlob().BuildSimple();

			i = 0;
			for(Cell c:frame.getGameScreen().getBlob().getCellsInGame())
			{
				if(c.getIsAlive())
				{
					i++;
					if(i == 1 || i == 3)
					{
						c.setColor(Color.RED);
					}
				}
			}
			
			frame.getUserPanel().getTutorial().setEnabled(false);
			frame.getUserPanel().getAutoRunToggleButton().setEnabled(false);
			frame.getUserPanel().getLoadBlobButton().setEnabled(false);
			frame.getUserPanel().getAdvanceToggleButton().setEnabled(true);
			frame.getUserPanel().getStatPanel().setVisible(true);
			frame.getUserPanel().getStatOptionPanel().setVisible(false);
			frame.UpdateLabels();
			
			message = "Now, I will click the 'Advance Simulation' button to advance the simulation one generation";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (10 of 13)", JOptionPane.INFORMATION_MESSAGE);
			frame.getUserPanel().getAdvanceToggleButton().doClick();
			
			message = "Notice how the cell in the center has remained alive (it had two neighbors).";
			message += "\nThe two red cells on the top and bottom each only had one neighbor (so they each died)";
			message += "\nNotice that two new cells to the left and right of the center cell were born.  They had been dead cells, but they each had two live neighbors.";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (11 of 13)", JOptionPane.INFORMATION_MESSAGE);

			message = "Experiment around by loading different groups of cells and then choosing to advance the simulation manually or run it automatically.";
			message += "\nOnce a set of cells is loaded, click the 'Auto Run' or 'Advance Simulation' buttons to move the simulation to the next generation.";
			message += "\nThe 'Acorn' is an interesting pattern.  From an initial set of seven cells, it lasts for 5206 generations before reaching a steady population of 633 cells (including some that escape).";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (12 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			message = "TO DO:  This code is not complete.";
			message += "\nI need to fix some run-time concurrency errors, add more GUI options and resize/rescale/recenter options, speed controls, mouse listeners, zoom/pan, back-buffering, etc";
			JOptionPane.showMessageDialog(frame, message, "Tutorial (13 of 13)", JOptionPane.INFORMATION_MESSAGE);
			
			Cell.setSize(3);
			frame.repaint();
			
			frame.getUserPanel().getStatOptionPanel().setVisible(true);
			frame.getUserPanel().getStatPanel().setVisible(true);
			frame.getUserPanel().getAutoRunToggleButton().setEnabled(true);
			frame.getUserPanel().getLoadBlobButton().setEnabled(true);
			frame.getGameScreen().getBlob().BuildClean();
		}
		
		if(e.getActionCommand().equals(LoadBlobButtonCommand))
		{
			Object[] options = {"Glider", "Glider Gun", "Oscillator", "Block",
								"Methuselah", "Acorn", "Galaxy", "Random", "Clear All"};
			
			String s = (String)JOptionPane.showInputDialog(frame, "Enter the initial set of cells...",
					"Cell Load Options", JOptionPane.PLAIN_MESSAGE, null, options, "Glider");
			
			if(s == null)
			{
				s = "Cancel";
			}
			CellBuildStartUp();
			switch(s)
			{
				case "Glider":
					frame.getGameScreen().getBlob().BuildGlider();
					break;
				case "Glider Gun":
					frame.getGameScreen().getBlob().BuildGliderGun();
					break;
				case "Oscillator":
					frame.getGameScreen().getBlob().BuildOscillator();
					break;
				case "Block":
					frame.getGameScreen().getBlob().BuildBlock();
					break;
				case "Methuselah":
					frame.getGameScreen().getBlob().BuildMethuselah();
					break;
				case "Acorn":
					frame.getGameScreen().getBlob().BuildAcorn();
					break;
				case "Simple":
					frame.getGameScreen().getBlob().BuildSimple();
					break;
				case "Galaxy":
					frame.getGameScreen().getBlob().BuildKokGalaxy();
					break;
				case "Random":
					frame.getGameScreen().getBlob().BuildRandom(0.5);
					break;
				case "Clear All":
					frame.getGameScreen().getBlob().BuildClean();
					frame.getUserPanel().getAdvanceToggleButton().setEnabled(false);
					frame.getUserPanel().getAutoRunToggleButton().setEnabled(false);
					frame.getUserPanel().getShowCentroid().setEnabled(false);
					frame.getGameScreen().getBlob().setAge(0);
					break;
				case "Cancel":
					break;
				default: 
					break;
			}
			if(s != "Cancel")
			{
				CellBuildCleanUp();
				
				//	Initialize statistics
				int countVal = frame.getGameScreen().getBlob().getLiveCellCount();
				frame.getUserPanel().updateCountLabel(countVal);
				
				Centroid point = frame.getGameScreen().getBlob().getCentroid();
				frame.getUserPanel().updateCentroid(point);
				
				double averageCentroidDist = frame.getGameScreen().getBlob().getAverageCentroidDistance();
				frame.getUserPanel().updateAverageDistanceToCentroidLabel(averageCentroidDist);
				double varianceCentroidDist = frame.getGameScreen().getBlob().getVarianceCentroidDistance();
				frame.getUserPanel().updateVarianceDistanceToCentroidLabel(varianceCentroidDist);
				
				frame.getUserPanel().getAdvanceToggleButton().setEnabled(true);			
				
				frame.getUserPanel().updateXRangeLabel(frame.getGameScreen().getBlob().getBoundary().getMinX(), frame.getGameScreen().getBlob().getBoundary().getMaxX());
				frame.getUserPanel().updateYRangeLabel(frame.getGameScreen().getBlob().getBoundary().getMinY(), frame.getGameScreen().getBlob().getBoundary().getMaxY());
				
				frame.getUserPanel().getStatOptionPanel().setVisible(true);
				frame.getUserPanel().getStatPanel().setVisible(true);
			}	
		}
		
		if(e.getActionCommand().equals(AutoRunToggleCommand))
		{
			if(frame.getUserPanel().getAutoRunToggleButton().getText() == "Auto Run: Off")
			{
				frame.getUserPanel().getAutoRunToggleButton().setText("Auto Run: On");
				frame.setRunning(true);
				frame.getUserPanel().getAdvanceToggleButton().doClick();
				frame.getUserPanel().getAdvanceToggleButton().setEnabled(false);
				frame.getUserPanel().getLoadBlobButton().setEnabled(false);
			}
			
			else if(frame.getUserPanel().getAutoRunToggleButton().getText() == "Auto Run: On")
			{
				frame.getUserPanel().getAutoRunToggleButton().setText("Auto Run: Off");
				frame.getUserPanel().getAdvanceToggleButton().setEnabled(true);
				frame.getUserPanel().getLoadBlobButton().setEnabled(true);
				frame.setRunning(false);
			}
			//frame.Rotate();
		}
	}
	
	public void CellBuildCleanUp()
	{
		frame.getGameScreen().setVisible(true);
		frame.getUserPanel().getAutoRunToggleButton().setEnabled(true);
		frame.getUserPanel().getShowCentroid().setEnabled(true);
		frame.getUserPanel().getUpCellSizeButton().setEnabled(true);
		frame.getUserPanel().getDownCellSizeButton().setEnabled(true);
		frame.repaint();
	}
	
	public void CellBuildStartUp()
	{
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.SOUTHWEST;
		constraints.fill = GridBagConstraints.BOTH;
		//frame.getLoadCellPanel().setVisible(false);
		//frame.getContentPane().remove(frame.getLoadCellPanel());
		//frame.getContentPane().add(frame.getGameScreen(),constraints);
	}
}
