package SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		if(e.getActionCommand().equals(LoadBlobButtonCommand))
		{
			//frame.getGameScreen().getBlob().BuildRandom(0.5);
			//frame.getGameScreen().getBlob().BuildBlock();
			//frame.getGameScreen().getBlob().BuildMethuselah();
			frame.getGameScreen().getBlob().BuildAcorn();
			//frame.getGameScreen().getBlob().BuildKokGalaxy();
			frame.Repaint();
			frame.getUserPanel().updateAgeLabel(0);
			
			int countVal = frame.getGameScreen().getBlob().getLiveCellCount();
			frame.getUserPanel().updateCountLabel(countVal);
			
			frame.getUserPanel().getAdvanceToggleButton().setVisible(true);
			frame.getUserPanel().getAgeLabel().setVisible(true);
			frame.getUserPanel().getCountLabel().setVisible(true);
			frame.getUserPanel().getAutoRunToggleButton().setVisible(true);
			
			
			frame.getUserPanel().updateXRangeLabel(frame.getGameScreen().getBlob().getBoundary().getMinX(), frame.getGameScreen().getBlob().getBoundary().getMaxX());
			frame.getUserPanel().updateYRangeLabel(frame.getGameScreen().getBlob().getBoundary().getMinY(), frame.getGameScreen().getBlob().getBoundary().getMaxY());
			frame.getUserPanel().getXRangeLabel().setVisible(true);
			frame.getUserPanel().getYRangeLabel().setVisible(true);			
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
}
