package SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameOfLife.Blob;
import GameOfLife.Cell;

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
		String StartToggleCommand = frame.getUserPanel().getAdvanceToggleButton().getActionCommand();
		String LoadBlobButtonCommand = frame.getUserPanel().getLoadBlobButton().getActionCommand();
		String RefreshButtonCommand = frame.getUserPanel().getRefreshButton().getActionCommand();
		
		if(e.getActionCommand().equals(StartToggleCommand))
		{
			if(frame.getUserPanel().getAdvanceToggleButton().getText() == "Advance Simulation")
			{
				frame.getUserPanel().getAdvanceToggleButton().setText("Working...");
				frame.Execute();
				frame.setRunning(true);
			}
			else if(frame.getUserPanel().getAdvanceToggleButton().getText() == "Working...")
			{
				frame.getUserPanel().getAdvanceToggleButton().setText("Advance Simulation");
				frame.setRunning(false);
			}
		}
		
		if(e.getActionCommand().equals(LoadBlobButtonCommand))
		{
			//frame.getGameScreen().getBlob().BuildRandom(0.8);
			frame.getGameScreen().getBlob().BuildBlock();
			frame.Repaint();
			frame.getUserPanel().updateAgeLabel(0);
			
			int countVal = frame.getGameScreen().getBlob().getLiveCellCount();
			frame.getUserPanel().updateCountLabel(countVal);
			
			frame.getUserPanel().getAdvanceToggleButton().setVisible(true);
			frame.getUserPanel().getAgeLabel().setVisible(true);
			frame.getUserPanel().getCountLabel().setVisible(true);
			
			
			frame.getUserPanel().updateXRangeLabel(frame.getGameScreen().getBlob().getBoundary().getMinX(), frame.getGameScreen().getBlob().getBoundary().getMaxX());
			frame.getUserPanel().updateYRangeLabel(frame.getGameScreen().getBlob().getBoundary().getMinY(), frame.getGameScreen().getBlob().getBoundary().getMaxY());
			frame.getUserPanel().getXRangeLabel().setVisible(true);
			frame.getUserPanel().getYRangeLabel().setVisible(true);			
		}
		
		if(e.getActionCommand().equals(RefreshButtonCommand))
		{
			int count = frame.getGameScreen().getBlob().getLiveCellCount();
			frame.getUserPanel().updateCountLabel(count);
			Blob blob = frame.getGameScreen().getBlob();
			
			count = 0;
			for(Cell c:blob.getCellsInGame())
			{
				if(c.getIsAlive())
				{
					count++;
				}
			}
			frame.getUserPanel().updateCountLabel(count);
		}
	}
}
