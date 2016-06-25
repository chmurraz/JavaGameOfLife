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
		String StartToggleCommand = frame.getUserPanel().getAdvanceToggleButton().getActionCommand();
		String LoadBlobButtonCommand = frame.getUserPanel().getLoadBlobButton().getActionCommand();
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
				//frame.Cancel();
				frame.setRunning(false);
			}
		}
		
		if(e.getActionCommand().equals(LoadBlobButtonCommand))
		{
			frame.getGameScreen().getBlob().BuildRandom(0.5);
			frame.Repaint();
			frame.getUserPanel().updateAgeLabel(0);
			int countVal = frame.getGameScreen().getBlob().getLiveCellCount();
			frame.getUserPanel().updateCountLabel(countVal);
			frame.getUserPanel().getAdvanceToggleButton().setVisible(true);
			frame.getUserPanel().getAgeLabel().setVisible(true);
			frame.getUserPanel().getCountLabel().setVisible(true);
			
		}
	}

}
