package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameOfLife.Blob.Centroid;
import GameOfLife.IntPoint2D;

public class MyListener implements ActionListener
{
	private final Frame frame;
	
	public MyListener(Frame frameVal)
	{
		frame = frameVal;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String AdvanceToggleCommand = frame.getUserPanel().getAdvanceToggleButton().getActionCommand();
		String LoadBlobButtonCommand = frame.getUserPanel().getLoadBlobButton().getActionCommand();
		String AutoRunToggleCommand = frame.getUserPanel().getAutoRunToggleButton().getActionCommand();
		String ShowCentroidToggleCommand = frame.getUserPanel().getShowCentroid().getActionCommand();
		
		String Glider = frame.getLoadCellPanel().getLoadGlider().getActionCommand();
		String Oscillator = frame.getLoadCellPanel().getLoadOscillator().getActionCommand();
		String Block = frame.getLoadCellPanel().getLoadBlock().getActionCommand();
		String Methuselah = frame.getLoadCellPanel().getLoadMethuselah().getActionCommand();
		String Acorn = frame.getLoadCellPanel().getLoadAcorn().getActionCommand();
		String Simple = frame.getLoadCellPanel().getLoadSimple().getActionCommand();
		String Galaxy = frame.getLoadCellPanel().getLoadKokGalaxy().getActionCommand();
		String Random = frame.getLoadCellPanel().getLoadRandom().getActionCommand();
		
		
		if(e.getActionCommand().equals(Glider))
		{
			frame.getGameScreen().getBlob().BuildGlider();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Oscillator))
		{
			frame.getGameScreen().getBlob().BuildOscillator();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Block))
		{
			frame.getGameScreen().getBlob().BuildBlock();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Methuselah))
		{
			frame.getGameScreen().getBlob().BuildMethuselah();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Acorn))
		{
			frame.getGameScreen().getBlob().BuildAcorn();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Simple))
		{
			frame.getGameScreen().getBlob().BuildSimple();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Galaxy))
		{
			frame.getGameScreen().getBlob().BuildKokGalaxy();
			CellBuildCleanUp();
		}
		if(e.getActionCommand().equals(Random))
		{
			frame.getGameScreen().getBlob().BuildRandom(0.5);
			CellBuildCleanUp();
		}
		
		if(e.getActionCommand().equals(AdvanceToggleCommand))
		{
			if(frame.getUserPanel().getAdvanceToggleButton().getText() == "Advance Simulation")
			{
				frame.getUserPanel().getAdvanceToggleButton().setText("Working...");
				frame.Execute();
			}
			else if(frame.getUserPanel().getAdvanceToggleButton().getText() == "Working...")
			{
				frame.getUserPanel().getAdvanceToggleButton().setText("Advance Simulation");
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
			}
			else if(frame.getUserPanel().getShowCentroid().getText() == "Show Centroid: On")
			{
				frame.getUserPanel().getShowCentroid().setText("Show Centroid: Off");
				frame.getUserPanel().getCentroidLabel().setVisible(false);
				frame.getUserPanel().getAverageDistanceToCentroidLabel().setVisible(false);
				frame.getUserPanel().getVarianceDistanceToCentroid().setVisible(false);
				frame.getGameScreen().getBlob().setDrawCentroid(false);
			}
		}
		
		if(e.getActionCommand().equals(LoadBlobButtonCommand))
		{
			frame.remove(frame.getGameScreen());
			frame.getLoadCellPanel().setVisible(true);
			
			frame.Repaint();
			frame.getUserPanel().updateAgeLabel(0);
			frame.getUserPanel().updateEscapedCellsCount(0);
			
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
		}
	}
	
	public void CellBuildCleanUp()
	{
		Container c = frame.getContentPane();
		
		frame.getLoadCellPanel().setVisible(false);
		c.add(frame.getGameScreen(), BorderLayout.CENTER);
		frame.getGameScreen().setVisible(true);
	}
}
